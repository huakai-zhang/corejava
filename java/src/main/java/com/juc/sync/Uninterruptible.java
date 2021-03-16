package com.juc.sync;

/**
 * 注释 t1.interrupt()，打开 this.notifyAll() 表示 notify 后 t1 的线程状态
 * 注释 this.notifyAll()，打开 t1.interrupt() 表示 interrupt 后 t1 的线程状态
 */
public class Uninterruptible {

    public synchronized void test() {
        System.out.println(Thread.currentThread().getName() + "进入同步代码块");
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void test1() {
        System.out.println(Thread.currentThread().getName() + "进入同步代码块");
        //this.notifyAll();
        try {
            Thread.sleep(88888);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Uninterruptible u = new Uninterruptible();
        Thread t1 = new Thread(() -> u.test());
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(() -> {
            t1.interrupt();
            u.test1();
        });
        t2.start();

        Thread.sleep(1000);
        System.out.println(t1.getState());
    }
}
