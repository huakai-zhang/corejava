package com.juc.core;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/15
 */
public class VisibilityTest {
    private static boolean ready;
    private static int number;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!ready) {
                System.out.println(ready);
            }
            System.out.println(number);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            number = 100;
            ready = true;
        }).start();
    }
}
