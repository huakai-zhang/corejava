package com.juc.volatiles;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/17
 */
public class UseVolatile02 {

    int a = 1;
    int b = 2;
    int c = 3;
    volatile boolean flag = false;

    public void write() {
        a = 100;
        b = 200;
        c = 300;
        flag = true;
    }

    public void read() {
        while (flag) {
            // flag被volatile修饰，充当了触发器，一旦值为true,此处立即对变量之前的操作可见。
            System.out.println("a = " + a + ",b = " + b + ", c = " + c);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseVolatile02 useVolatile02 = new UseVolatile02();
        new Thread(() -> {
            useVolatile02.write();
        }).start();
        new Thread(() -> {
            useVolatile02.read();
        }).start();
    }
}
