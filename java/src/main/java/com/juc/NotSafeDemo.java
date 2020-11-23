package com.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 集合类是不安全的
 * 1. 故障现象 java.util.ConcurrentModificationException
 * 解决方案
 *  Vector
 *  Collections.synchronizedList(new ArrayList<>())
 *  new CopyOnWriteArrayList<>()
 */
public class NotSafeDemo {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap();
        Set<String> list = new HashSet<>();//Collections.synchronizedList(new ArrayList<>());//new Vector<>(); //new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        /*List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());//new Vector<>(); //new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }*/
    }
}
