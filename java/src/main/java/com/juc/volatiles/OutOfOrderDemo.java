package com.juc.volatiles;

public class OutOfOrderDemo {
    public volatile static int a = 0, b = 0;
    public volatile static int i = 0, j = 0;

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        while (true) {
            count++;
            a = 0;
            b = 0;
            i = 0;
            j = 0;
            Thread one = new Thread(() -> {
                a = 1;
                i = b;
            });

            Thread two = new Thread(() -> {
                b = 1;
                j = a;
            });

            two.start();
            one.start();
            one.join();
            two.join();

            System.out.println("第" + count + "次输出：i = " + i + ",j = " + j);
            if (i == 0 && j == 0) {
                break;
            }
        }
    }
}
