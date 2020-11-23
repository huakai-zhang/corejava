package com.juc.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/15
 */
public class AtomicIntegerTest {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                /*try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                atomicInteger.getAndIncrement();
            }).start();
        }

        Thread.sleep(3000);
        System.out.println(atomicInteger.get());
    }
}
