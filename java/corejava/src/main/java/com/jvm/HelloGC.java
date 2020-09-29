package com.jvm;

import java.util.Random;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/28
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC
 *
 * (已移除)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello GC");
        /*try {
            String str = "Spring";
            while(true) {
                str += str + new Random().nextInt(7777777) + new Random().nextInt(88888888);
                str.intern();
            }
        } catch (Throwable r) {
            r.printStackTrace();
        }*/
        //Thread.sleep(Integer.MAX_VALUE);
    }
}
