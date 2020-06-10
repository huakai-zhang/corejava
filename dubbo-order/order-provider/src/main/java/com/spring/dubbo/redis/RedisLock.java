package com.spring.dubbo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;

public class RedisLock {

    public String getLock(String key, int timeout) {
        try {
            Jedis jedis = RedisManager.getJedis();
            String value = UUID.randomUUID().toString();

            long end = System.currentTimeMillis() + timeout;
            // 阻塞
            while (System.currentTimeMillis() < end) {
                if (jedis.setnx(key, value) == 1) {
                    jedis.expire(key, timeout);
                    // 锁设置成功，redis 操作成功
                    return value;
                }
                if (jedis.ttl(key) == -1) {
                    jedis.expire(key, timeout);
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean releaseLock(String key, String value) {
        try {
            Jedis jedis = RedisManager.getJedis();
            while (true) {
                jedis.watch(key);
                // 判断获得锁的线程和当前 redis 中存的锁是同一个
                if (value.equals(jedis.get(key))) {
                    Transaction transaction = jedis.multi();
                    transaction.del(key);

                    List<Object> list = transaction.exec();
                    if (list == null) {
                        continue;
                    }
                    return true;
                }
                jedis.unwatch();
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        RedisLock lock = new RedisLock();
        String lockId = lock.getLock("lock:first", 10000);
        if (null != lockId) {
            System.out.println("获得锁成功");
        }

        String lockId1 = lock.getLock("lock:first", 10000);
        System.out.println(lockId1);
    }
}
