package com.juc.core;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/21
 */
public class InterruptionInJava {

    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(() -> {
            while(!Thread.currentThread().isInterrupted()){
                System.out.println("I am still running");
            }
            System.out.println("使用 interrupt() + isInterrupted()来中断线程，线程结束了");
        });
        testThread.start();
        Thread.sleep(100);

        testThread.interrupt();
        Thread.sleep(100);
        System.out.println("main end");
    }
}
