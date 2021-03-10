package com.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 春阳
 * @date 2021-03-10 16:28
 */
public class LockInterruptiblyTest {
    private static Lock lock = new ReentrantLock();

    private static int num = 1;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    print();
                } catch (InterruptedException e) {
                    System.out.println("等待线程被中断");
                }
            }
        }, "A");
        t1.start();
        Thread.sleep(10);
        t2.start();
        Thread.sleep(500);
        System.out.println("5s后不再等待");
        t2.interrupt();
        System.out.println(num);
    }

    public static void print() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            num++;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
