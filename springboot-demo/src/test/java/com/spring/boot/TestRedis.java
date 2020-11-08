package com.spring.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        // 设置 string
        //redisTemplate.opsForValue().set("laozhang", "张三");
        // 获取 string
        System.out.println(redisTemplate.opsForValue().get("laozhang"));
        // 设置过期时间
        //redisTemplate.expire("laozhang", 10, TimeUnit.SECONDS);

        // 设置hash
        //redisTemplate.opsForHash().put("employee:laoli", "sex", "男");
        // 获取hash
        //System.out.println(redisTemplate.opsForHash().get("employee:laoli", "name"));
    }
}
