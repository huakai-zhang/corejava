package com.juc.sync;

public class VisibilityTest {
    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (flag) {
                // 增加对象共享数据的打印，println是同步方法
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("flag = " + flag);
                // System.out.println 源码
                /* public void println(String x) {
                    synchronized (this) {
                        print(x);
                        newLine();
                    }
                } */
            }
        }).start();

        Thread.sleep(2000);

        new Thread(() -> {
            flag = false;
            System.out.println("线程修改了变量的值为false");
        }).start();
    }
}
