package com.juc.sync;

public class Demo01 {
    private static Object obj = new Object();

    public static void main(String[] args) {
        synchronized (obj) {
            System.out.println("b");
        }
    }

    public synchronized void test() {
        System.out.println("a");
    }
}
