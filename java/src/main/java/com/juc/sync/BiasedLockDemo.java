package com.juc.sync;

import org.openjdk.jol.info.ClassLayout;

public class BiasedLockDemo {
    public static void main(String[] args) {
        Object obj = new Object();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (obj) {
                    System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                }
            }
        }).start();
    }
}
