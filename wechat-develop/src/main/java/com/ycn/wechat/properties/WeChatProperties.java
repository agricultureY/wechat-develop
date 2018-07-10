package com.ycn.wechat.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信配置类
 * @author ycn
 * @package com.ycn.wechat.properties
 * @ClassName WeChatProperties
 * @Date 2018/7/9 10:29
 */
@Configuration
public class WeChatProperties {

    @Value("${wechat.token}")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
