package com.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;

public class JedisMonitorTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.25.128", 6379);
        jedis.auth("1234");
        jedis.monitor(new JedisMonitor() {
            @Override
            public void onCommand(String command) {
                System.out.println("#monitor: " + command);
            }
        });
    }
}
