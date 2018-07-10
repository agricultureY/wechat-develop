package com.ycn.wechat;

import com.ycn.wechat.properties.RedisProperties;
import com.ycn.wechat.properties.WeChatProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatDevelopApplicationTests {

    @Autowired
    WeChatProperties properties;

    @Test
    public void contextLoads() {
        System.out.println(properties.getToken());
        System.out.println(RedisProperties.getAuth());
        System.out.println(RedisProperties.getMaxActive());
        System.out.println(RedisProperties.getUrl());
    }

}
