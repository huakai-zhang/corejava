package com.juc.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 春阳
 * @date 2021-03-22 19:35
 */
public class ScheduledExecutorServiceTest {

    public static void main(String[] args) {
        AtomicLong prev = new AtomicLong(System.currentTimeMillis());
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(100);
        //executorService.scheduleAtFixedRate(() -> { // 1000
        executorService.scheduleWithFixedDelay(() -> { // 1100
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": " + (System.currentTimeMillis() - prev.longValue()));
            prev.set(System.currentTimeMillis());
        }, 0, 100, TimeUnit.MILLISECONDS);
        /*executorService.schedule(() -> {
            System.out.println(123);
        }, 1000, TimeUnit.MILLISECONDS);*/
    }

}
