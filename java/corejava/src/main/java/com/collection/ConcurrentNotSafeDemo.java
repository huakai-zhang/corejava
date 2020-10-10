package com.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/10/10
 */
public class ConcurrentNotSafeDemo {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());//new Vector<>();//new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
// java.util.ConcurrentModificationException