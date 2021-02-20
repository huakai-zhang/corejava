package com.jvm;

import com.sun.java.accessibility.util.EventID;

public class MyObject {
    public static void main(String[] args) {
        Object object = new Object();
        //System.out.println(object.getClass().getClassLoader().getParent().getParent()); NullPointerException
        //System.out.println(object.getClass().getClassLoader().getParent()); NullPointerException
        System.out.println(object.getClass().getClassLoader());

        // com.sun.java.accessibility.util.EventID --> %JRE_HOME%\lib\ext\jaccess.jar 中的类
        EventID zipFileStore = new EventID();
        System.out.println(zipFileStore.getClass().getClassLoader().getParent());
        System.out.println(zipFileStore.getClass().getClassLoader());

        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());
        System.out.println(myObject.getClass().getClassLoader().getParent());
        System.out.println(myObject.getClass().getClassLoader());
    }
}