package com.juc.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/17
 */
public class UseVolatile implements Runnable {
    volatile boolean flag = false;
    AtomicInteger realA = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            setDone();
            realA.incrementAndGet();
        }
    }

    private void setDone() {
        flag = true;// 纯赋值操作符合预期
        // flag = !flag ; // 这样做不符合预期
    }

    public static void main(String[] args) throws InterruptedException {
        UseVolatile u = new UseVolatile();
        Thread t1 = new Thread(u);
        Thread t2 = new Thread(u);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(u.flag);
        System.out.println(u.realA.get());
    }
}
