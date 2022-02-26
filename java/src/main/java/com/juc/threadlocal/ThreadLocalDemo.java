package com.juc.threadlocal;

public class ThreadLocalDemo {
    static ThreadLocal<Integer> num = ThreadLocal.withInitial(() -> 0);

    static ThreadLocal<String> str = ThreadLocal.withInitial(() -> "Hello ");

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(num.get());
            System.out.println(str.get());
        });
        thread.start();
    }
}
