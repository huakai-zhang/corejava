package com.juc;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 春阳
 * @date 2020-12-28 16:49
 */
public class ReentrantLockDemo {

    private static int count = 0;
    static Lock lock = new ReentrantLock();

    public void incr() {
        lock.lock();
        try {
            count++;
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                demo.incr();
            }, "Thread" + i).start();
            Thread.sleep(500);
        }
        //Thread.sleep(3000);
        //System.out.println("result:"+count);
        System.in.read();
    }
}
