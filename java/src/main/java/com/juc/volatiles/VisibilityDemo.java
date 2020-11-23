package com.juc.volatiles;

/**
 * 多线程下变量访问的不可变性
 */
class MyThread {
    private volatile boolean flag = false;

    public void run() {
        flag = true;
        System.out.println("flag= " + flag);
    }

    public boolean isFlag() {
        return flag;
    }
}
public class VisibilityDemo {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.run();
        }).start();

        while (true) {
            //synchronized(t) {
                if (t.isFlag()) {
                    System.out.println("--------------");
                    break;
                }
            //}
        }
    }
}
