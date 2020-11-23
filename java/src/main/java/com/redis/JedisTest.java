package com.redis;

import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("1234");
        jedis.set("spring", "666");
        System.out.println(jedis.get("spring"));
        jedis.close();
    }
}
