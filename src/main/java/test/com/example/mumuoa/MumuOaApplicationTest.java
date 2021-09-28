package test.com.example.mumuoa;

import cn.hutool.core.util.IdUtil;
import com.example.mumuoa.MumuOaApplication;
import com.example.mumuoa.db.pojo.MessageEntity;
import com.example.mumuoa.db.pojo.MessageRefEntity;
import com.example.mumuoa.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MumuOaApplication.class)
public class MumuOaApplicationTest {
    @Resource
    private MessageService messageService;


    @Test
    public void contextLoads() {
        for (int i = 1; i <= 100; i++) {
            MessageEntity message = new MessageEntity();
            message.setUuid(IdUtil.simpleUUID());
            message.setSenderId(0);
            message.setSenderName("系统消息");
            message.setMsg("这是第" + i + "条测试消息");
            message.setSenderTime(new Date());
            String id = messageService.insertMessage(message);

            MessageRefEntity ref = new MessageRefEntity();
            ref.setMessageId(id);
            ref.setReceiverId(21); //接收人ID
            ref.setLastFlag(true);
            ref.setReadFlag(false);
            messageService.insertMessageRef(ref);
        }
    }
} 