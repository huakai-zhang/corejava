package com.juc.core;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/17
 */
public class NotifyTest {
    public synchronized void testWait() {
        System.out.println(Thread.currentThread().getName() + "Start------");
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "End------");
    }

    public static void main(String[] args) throws InterruptedException {
        final NotifyTest test = new NotifyTest();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> test.testWait()).start();
        }
        Thread.sleep(500);
        synchronized (test) {
            test.notify();
        }
        Thread.sleep(3000);
        System.out.println("----------------------------------");
        synchronized (test) {
            test.notifyAll();
        }
    }
}
