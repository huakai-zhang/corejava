package com.juc.volatiles;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/15
 */
public class NoneAtomicTest {
    private int i;

    public void addI() {
        synchronized(this) {
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final NoneAtomicTest test = new NoneAtomicTest();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.addI();
            }).start();
        }

        Thread.sleep(3000);
        System.out.println(test.i);
    }
}
