package com.juc.core;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/16
 */
public class YieldTest implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        YieldTest test = new YieldTest();
        Thread t1 = new Thread(test, "FirstThread");
        Thread t2 = new Thread(test, "SecondThread");

        t1.start();
        t2.start();
    }
}
