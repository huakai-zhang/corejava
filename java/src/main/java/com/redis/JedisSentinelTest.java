package com.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class JedisSentinelTest {
    private static JedisSentinelPool pool;

    private static JedisSentinelPool createJedisPool() {
        // master的名字是sentinel.conf配置文件里面的名称
        String masterName = "redis-master";
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("192.168.8.203:26379");
        sentinels.add("192.168.8.204:26379");
        sentinels.add("192.168.8.205:26379");
        pool = new JedisSentinelPool(masterName, sentinels, "1234");
        return pool;
    }

    public static void main(String[] args) {
        JedisSentinelPool pool = createJedisPool();
        System.out.println(pool.getCurrentHostMaster());
        Jedis jedis = pool.getResource();
        jedis.set("xiaoxiao", "520-"+System.currentTimeMillis());
        System.out.println(jedis.get("xiaoxiao"));
    }
}
