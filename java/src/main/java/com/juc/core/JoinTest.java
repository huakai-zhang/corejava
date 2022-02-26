package com.juc.core;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/16
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        test1();
        //test2();
    }

    public static void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是Thread1");
        });

        Thread t2 = new Thread(() -> {
            try {
                // t2 需要等待 t1 执行完毕（字面意思就是 t1 在此处 join 进来）
               t1.join();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            System.out.println("我是Thread2");
        });

        t1.start();
        Thread.sleep(10);
        t2.start();
    }

    public static void test2() throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            Thread t = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " start------");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end------");
            }, "线程" + i);
            t.start();
            t.join();
        }
        System.out.println("Finished~~~");
    }
}
