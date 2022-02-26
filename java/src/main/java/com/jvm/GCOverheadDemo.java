package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/30
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("==========i: " + i);
            e.printStackTrace();
        }
    }
}
// -Xms20m -Xmx20m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m