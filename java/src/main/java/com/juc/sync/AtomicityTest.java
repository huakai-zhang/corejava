package com.juc.sync;

import java.util.ArrayList;
import java.util.List;

public class AtomicityTest {

    private static int number;
    private static Object obj = new Object();

    public static void add() {
        synchronized (obj) {
            number++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(number);
    }
}
