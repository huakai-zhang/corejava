package com.juc.core;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/21
 */
public class InterruptionInJava01 {
    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(() -> {
            try {
                Thread.sleep(888888);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println("caught exception right now: "+e);
            }
        });
        testThread.start();
        Thread.sleep(10);

        testThread.interrupt();
        Thread.sleep(10);
        System.out.println("main end");
    }
}
