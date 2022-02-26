package com.juc.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.atomic.AtomicInteger;

public class OpenJdkJolDemo {
    public static void main(String[] args) {
        Jol jol = new Jol();
        //System.out.println(jol.hashCode());
        //System.out.println(Integer.toHexString(jol.hashCode()));
        System.out.println(ClassLayout.parseInstance(jol).toPrintable());
    }
}
