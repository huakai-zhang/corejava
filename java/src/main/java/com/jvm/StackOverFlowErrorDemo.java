package com.jvm;

public class StackOverFlowErrorDemo {
    private int stackLength = 1;

    public static void main(String[] args) {
        StackOverFlowErrorDemo demo = new StackOverFlowErrorDemo();
        try {
            demo.m1();
        } catch (Throwable e) {
            System.out.println("stack length: " + demo.stackLength);
            throw e;
        }
    }

    public void m1() {
        stackLength++;
        m1();
    }
}
// Exception in thread "main" java.lang.StackOverflowError
