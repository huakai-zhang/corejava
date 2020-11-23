package com.jvm;

import java.util.Random;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/4
 */
public class MemoryOptionsDemo {
    public static void main(String[] args) {
        System.out.println("当前计算机核数 = " + Runtime.getRuntime().availableProcessors());

        //返回 Java 虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        //返回 Java 虚拟机中的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("-Xmx:MAX_MEMORY = " + maxMemory + "（字节）、" + (maxMemory / (double)1024 / 1024) + "MB");
        System.out.println("-Xms:TOTAL_MEMORY = " + totalMemory + "（字节）、" + (totalMemory / (double)1024 / 1024) + "MB");
    }
}
// -Xms1024m -Xmx1024m -XX:+PrintGCDetails
