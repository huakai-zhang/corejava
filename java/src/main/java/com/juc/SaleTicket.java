package com.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    private int number = 30;
    private Lock lock = new ReentrantLock();

    public void saleTicket() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出第" + (number--) + "\t还剩下" + number);
            }
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 多线程编程的企业级套路+模版
 * 1. 在高内聚低耦合的前提下，线程   操作（对外暴露的调用方法）   资源类
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i <= 40 ; i++) {
                ticket.saleTicket();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i <= 40 ; i++) {
                ticket.saleTicket();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i <= 40 ; i++) {
                ticket.saleTicket();
            }
        }, "C").start();
    }
}
