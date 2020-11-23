package com.jvm;

public class StackOverFlowErrorDemo {
    public static void main(String[] args) {
        m1();
    }

    public static void m1() {
        m1();
    }
}
// Exception in thread "main" java.lang.StackOverflowError
