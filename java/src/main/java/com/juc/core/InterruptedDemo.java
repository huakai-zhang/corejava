package com.juc.core;

import java.util.concurrent.TimeUnit;

/**
 * @author 春阳
 * @date 2020-12-28 14:35
 */
public class InterruptedDemo {
    private static int i;
    public static void main(String[] args) throws InterruptedException {
        /*Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("before:" + Thread.currentThread().isInterrupted());
                    Thread.interrupted(); //对线程进行复位，由 true 变成 false
                    System.out.println("after:" + Thread.currentThread().isInterrupted());
                }
            }
        }, "InterruptedDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();*/
        /*Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Num:"+i);
        }, "InterruptedDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        System.out.println(thread.isInterrupted());*/
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(111);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }
            System.out.println("Num:"+i);
        }, "InterruptedDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
