package com.example.mumuoa;

import cn.hutool.core.util.StrUtil;
import com.example.mumuoa.config.SystemConstants;
import com.example.mumuoa.db.dao.SysConfigMapper;
import com.example.mumuoa.db.pojo.SysConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

@ServletComponentScan
@SpringBootApplication
@Slf4j
public class MumuOaApplication {
    @Resource
    SystemConstants constants;

    @Resource
    SysConfigMapper sysConfigMapper;

    public static void main(String[] args) {
        SpringApplication.run(MumuOaApplication.class, args);
    }

    @PostConstruct
    public void init() {
        List<SysConfig> list = sysConfigMapper.selectAllParam();
        list.forEach(one -> {
            String key = one.getParamKey();
            key = StrUtil.toCamelCase(key);
            String value = one.getParamValue();

            try {
                Field field = constants.getClass().getDeclaredField(key);
                field.set(constants, value);
                System.out.println("constants =====> " + constants);
                System.out.println("value =====> " + value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}