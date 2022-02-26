package com.jvm;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/29
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        // 强引用
        Object obj1 = new Object();
        // obj2引用赋值
        Object obj2 = obj1;
        obj1 = null;
        System.gc();
        System.out.println(obj2);
    }
}
// java.lang.Object@2ff4acd0