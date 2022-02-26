package com.jvm;

import com.juc.threadlocal.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/4
 */
public class JavaHeapSpaceDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        List<User> users = new ArrayList<>();
        //String str = "www.baidu.com" ;
        while(true) {
            System.out.println("......");
            users.add(new User("a"));
            Thread.sleep(1000);
            //str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999) ;
        }

    }
}
// str -Xms8m -Xmx8m -XX:+PrintGCDetails
// users -Xms8m -Xmx8m
// Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
