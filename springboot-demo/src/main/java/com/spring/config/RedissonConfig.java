package com.spring.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author 春阳
 * @date 2021-03-23 18:39
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        //1、创建配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.25.128:6379").setPassword("1234");
        return Redisson.create(config);
    }

}
