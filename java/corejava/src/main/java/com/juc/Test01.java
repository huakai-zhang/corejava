package com.juc;

public class Test01 {
    int number = 1;

    public void test () {
        synchronized(this) {
            while (number != 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("aaa");
        }
    }

    public volatile int n;

    public synchronized void add() {
        this.notifyAll();
    }

}
