package com.juc.sync;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/15
 */
public class SynchronizedTest {
    private void method1() {
        System.out.println("Method 1 start");
        try {
            synchronized(this) {
                System.out.println("Method 1 execute");
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    private void method2() {
        System.out.println("Method 2 start");
        try {
            synchronized(this) {
                System.out.println("Method 2 execute");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

    public static void main(String[] args) {
        final SynchronizedTest test = new SynchronizedTest();
        //final SynchronizedTest test2 = new SynchronizedTest();
        new Thread(() -> test.method1()).start();
        new Thread(() -> test.method2()).start();
    }
}
