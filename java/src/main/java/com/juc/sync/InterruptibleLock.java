package com.juc.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptibleLock {

    private static Lock lock = new ReentrantLock();

    public void test01() {
        // Lock 不可中断
        //lock.lock();
        boolean b = false;
        try {
            b = lock.tryLock(3, TimeUnit.SECONDS);
            // Lock 可中断
            if (b) {
                System.out.println(Thread.currentThread().getName() + "获得了锁");
                Thread.sleep(88888);
            } else {
                System.out.println(Thread.currentThread().getName() + "在指定时间没获得了锁做其他操作");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (b) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptibleLock i = new InterruptibleLock();
        Thread t1 = new Thread(() -> i.test01());
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(() -> i.test01());
        t2.start();

//        System.out.println("停止线程前");
//        t2.interrupt();
//        System.out.println("停止线程前");
//
//        Thread.sleep(1000);
//        System.out.println(t1.getState());
//        System.out.println(t2.getState());
    }

}
