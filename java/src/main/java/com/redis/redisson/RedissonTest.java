package com.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissonTest {
    private static RedissonClient redissonClient;

    static {
        Config config=new Config();
        config.useSingleServer().setAddress("redis://192.168.25.128:6379").setPassword("1234");
        redissonClient= Redisson.create(config);
    }

    public static void main(String[] args) throws InterruptedException {
        RLock rLock= redissonClient.getLock("updateAccount");
        // 最多等待 100 秒、上锁 60s 以后自动解锁
        if(rLock.tryLock(100,60, TimeUnit.SECONDS)){
            System.out.println("获取锁成功");
        } else {
            System.out.println("获取锁失败");
        }
        Thread.sleep(20000);
        rLock.unlock();
        redissonClient.shutdown();
    }
}
