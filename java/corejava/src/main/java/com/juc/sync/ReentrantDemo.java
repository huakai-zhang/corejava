package com.juc.sync;

public class ReentrantDemo {
    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
    }

    static void test() {
        synchronized (MyThread.class) {
            System.out.println(Thread.currentThread().getName() + "进入了同步代码块2");
        }
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        synchronized (MyThread.class) {
            System.out.println(getName() + "进入了同步代码块1");
            ReentrantDemo.test();
        }
    }
}
