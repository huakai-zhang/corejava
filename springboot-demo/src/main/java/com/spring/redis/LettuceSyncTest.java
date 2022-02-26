package com.spring.redis;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class LettuceSyncTest {
    public static void main(String[] args) {
        // 创建客户端
        RedisClient client = RedisClient.create(RedisURI.Builder.redis("192.168.25.128", 6379).withPassword("1234").build());
        // 线程安全的长连接，连接丢失时会自动重连
        StatefulRedisConnection<String, String> connection = client.connect();
        // 获取同步执行命令，默认超时时间为 60s
        RedisCommands<String, String> sync = connection.sync();
        // 发送get请求，获取值
        sync.set("spring:sync","lettuce-sync-666" );
        String value = sync.get("spring:sync");
        System.out.println("------"+value);
        //关闭连接
        connection.close();
        //关掉客户端
        client.shutdown();
    }
}
