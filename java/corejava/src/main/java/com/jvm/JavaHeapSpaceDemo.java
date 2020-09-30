package com.jvm;

import java.util.Random;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/4
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        String str = "www.baidu.com" ;
        while(true) {
            str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999) ;
        }

    }
}
// -Xms8m -Xmx8m -XX:+PrintGCDetails
// Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
