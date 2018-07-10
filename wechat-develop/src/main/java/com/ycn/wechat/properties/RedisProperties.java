package com.ycn.wechat.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * redis配置类
 *
 * @author ycn
 * @package com.ycn.wechat.properties
 * @ClassName RedisProperties
 * @Date 2018/7/9 15:41
 */
@Configuration
@ConfigurationProperties(prefix = RedisProperties.REDIS_PREFIX)
public class RedisProperties {

    public static final String REDIS_PREFIX = "redis";

    private static String url = "127.0.0.1";

    private static Integer port = 6379;

    private static String auth = "";

    private static Integer maxActive = 1024;

    private static Integer maxIdle = 200;

    private static Integer maxWait = 10000;

    private static Integer timeout = 10000;

    public static String getRedisPrefix() {
        return REDIS_PREFIX;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        RedisProperties.url = url;
    }

    public static Integer getPort() {
        return port;
    }

    public static void setPort(Integer port) {
        RedisProperties.port = port;
    }

    public static String getAuth() {
        return auth;
    }

    public static void setAuth(String auth) {
        RedisProperties.auth = auth;
    }

    public static Integer getMaxActive() {
        return maxActive;
    }

    public static void setMaxActive(Integer maxActive) {
        RedisProperties.maxActive = maxActive;
    }

    public static Integer getMaxIdle() {
        return maxIdle;
    }

    public static void setMaxIdle(Integer maxIdle) {
        RedisProperties.maxIdle = maxIdle;
    }

    public static Integer getMaxWait() {
        return maxWait;
    }

    public static void setMaxWait(Integer maxWait) {
        RedisProperties.maxWait = maxWait;
    }

    public static Integer getTimeout() {
        return timeout;
    }

    public static void setTimeout(Integer timeout) {
        RedisProperties.timeout = timeout;
    }

}
