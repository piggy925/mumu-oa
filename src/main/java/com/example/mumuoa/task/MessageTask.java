package com.example.mumuoa.task;

import com.example.mumuoa.db.pojo.MessageEntity;
import com.example.mumuoa.db.pojo.MessageRefEntity;
import com.example.mumuoa.exception.MumuoaException;
import com.example.mumuoa.service.MessageService;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class MessageTask {
    @Resource
    private ConnectionFactory factory;

    @Resource
    private MessageService messageService;

    public void send(String topic, MessageEntity entity) {
        String id = messageService.insertMessage(entity);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(topic, true, false, false, null);
            HashMap map = new HashMap();
            map.put("messageId", id);
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().headers(map).build();
            channel.basicPublish("", topic, properties, entity.getMsg().getBytes());
            log.debug("消息发送成功");
        } catch (Exception e) {
            log.info("消息发送异常", e);
            throw new MumuoaException("向MQ发送消息失败");
        }
    }

    @Async
    public void sendAsync(String topic, MessageEntity entity) {
        send(topic, entity);
    }

    public int receive(String topic) {
        int count = 0;
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(topic, true, false, false, null);
            while (true) {
                GetResponse response = channel.basicGet(topic, false);
                if (!ObjectUtils.isEmpty(response)) {
                    AMQP.BasicProperties properties = response.getProps();
                    Map<String, Object> map = properties.getHeaders();
                    String messageId = map.get("messageId").toString();
                    String message = new String(response.getBody());
                    log.debug("从RabbitMQ接收到的消息：" + message);

                    MessageRefEntity entity = new MessageRefEntity();
                    entity.setMessageId(messageId);
                    entity.setReceiverId(Integer.parseInt(topic));
                    entity.setReadFlag(false);
                    entity.setLastFlag(true);
                    messageService.insertMessageRef(entity);

                    long deliveryTag = response.getEnvelope().getDeliveryTag();
                    channel.basicAck(deliveryTag, false);
                    count++;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            log.info("消息发送异常", e);
            throw new MumuoaException("接收消息失败");
        }
        return count;
    }

    @Async
    public int reveiveAsync(String topic) {
        return receive(topic);
    }

    public void deleteQueue(String topic) {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDelete(topic);
            log.debug("消息队列成功删除");
        } catch (Exception e) {
            log.error("消息队列删除失败", e);
            throw new MumuoaException("消息队列删除失败");
        }
    }

    @Async
    public void deleteQueueAsync(String topic) {
        deleteQueue(topic);
    }
}