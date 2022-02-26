package com.spring.design.proxy;

/**
 * @author 春阳
 * @date 2021-01-29 18:19
 */
public class NewInstanceOfClass {
    public static void main(String[] args) throws Exception {
        // Create and Return String class
        StringBuilder s1 = new StringBuilder("a");
        Class cl1 = s1.getClass();

        // We are creating a new instance of the
        // class denoted by this object cl1
        // by using newInstance() method
        Object s2 = StringBuilder.class.newInstance();
        Class cl2 = s2.getClass();

        // Display Instance
        System.out.println("Instance s1: " + cl1.hashCode());
        System.out.println("Instance s2: " + cl2.hashCode());
    }

}
