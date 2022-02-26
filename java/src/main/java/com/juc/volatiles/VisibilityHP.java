package com.juc.volatiles;

public class VisibilityHP {
    private int a = 1;
    private int b = 2;

    public void write() {
        a = 3;
        b = a;
    }

    public void read() {
        System.out.println("b = " + b + ", a = " + a);
    }

    public static void main(String[] args) {
        while (true) {
            VisibilityHP hp = new VisibilityHP();
            new Thread(() -> hp.write()).start();

            new Thread(() -> hp.read()).start();
        }
    }
}
