package com.ali;

import com.google.common.util.concurrent.RateLimiter;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 令牌桶 ===》 只能实现单机限流
 * @author 花开不合阳春暮
 * @date 2021/3/1 下午9:24
 */
public class RateLimiterDemo {

    // 10 qps
    RateLimiter rateLimiter = RateLimiter.create(10);

    public void doTest() {
        // 获得一个令牌
        if (rateLimiter.tryAcquire()) {
            System.out.println("允许访问");
        } else {
            System.out.println("被限流了");
        }
    }

    public static void main(String[] args) throws IOException {
        RateLimiterDemo rate = new RateLimiterDemo();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    Thread.sleep(1000);
                    rate.doTest();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.countDown();
        System.in.read();
    }
}
