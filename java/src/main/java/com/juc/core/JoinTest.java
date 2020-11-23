package com.juc.core;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/16
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            Thread t = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " start------");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end------");
            }, "线程" + i);
            t.start();
            t.join();
        }
        System.out.println("Finished~~~");
    }
}
