package com.jvm;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/29
 *
 * **可以作为 GC Roots 的对象**
 * 虚拟机栈（栈帧中的局部变量区，也叫做局部变量表）中的引用对象
 * 方法区中的类静态属性引用的对象
 *
 * 本地方法栈中JNI(Native)引用的对象
 */
public class GCRootsDemo {

    // 方法区中的类静态属性引用的对象
    //private static GCRootsDemo2 demo2;
    // 方法区中常量引用的对象
    //private static final GCRootsDemo3 demo3 = new GCRootsDemo3(8);

    public static void m1() {
        // 虚拟机栈（栈帧中的局部变量区，也叫做局部变量表）中的引用对象
        GCRootsDemo t1 = new GCRootsDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
