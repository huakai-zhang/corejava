package com.juc.core;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/17
 */
public class SleepTest {
    public synchronized void sleepMethod() {
        System.out.println(Thread.currentThread().getName() + "Sleep start ------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "Sleep end ------");
    }

    public synchronized void waitMethod() {
        System.out.println(Thread.currentThread().getName() + "Wait start ------");
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "Wait end ------");
    }

    public static void main(String[] args) throws InterruptedException {
        final SleepTest test = new SleepTest();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> test.sleepMethod()).start();
        }


        Thread.sleep(5000);
        System.out.println("--------------------");

        final SleepTest test2 = new SleepTest();

        for(int i = 0;i < 3;i++){
            new Thread(() -> test2.waitMethod()).start();
        }

    }
}
