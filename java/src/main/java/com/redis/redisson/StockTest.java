package com.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author 春阳
 * @date 2021-03-22 13:45
 */
public class StockTest {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.25.128:6379").setPassword("1234");
        RedissonClient redissonClient = Redisson.create(config);

        int requireQty = 9;
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    RLock lock = redissonClient.getLock("stockLock");
                    System.out.println(Thread.currentThread().getName() + "  开始");

                    RAtomicLong stockQty = redissonClient.getAtomicLong("stockQty");
                    RAtomicLong stockOccupy = redissonClient.getAtomicLong("stockOccupy");
                    lock.lock();

                    long l1 = stockQty.get();
                    long l2 = stockOccupy.get();
                    long l = l1 - l2;

                    if (l >= requireQty) {
                        stockOccupy.set(stockOccupy.get() + requireQty);
                    }

                    if (l >= requireQty) {
                        System.out.println(Thread.currentThread().getName() + "  done，库存剩下：" + (l - requireQty));
                    } else {
                        System.out.println(Thread.currentThread().getName() + "  库存不足，库存剩下：" + l);
                    }

                    lock.unlock();
                    //创建订单，扣减库存
                    Thread.sleep(200);

                    System.out.println(Thread.currentThread().getName() + "  结束");
                } catch (Exception e) {

                }
            }).start();
        }
    }
}
