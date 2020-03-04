package com.spring.singleton;

import com.spring.singleton.seriable.Seriable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Spring Zhang
 * @date 2020/3/4 15:10
 */
public class ThreadSafeTest {
    public static void main(String[] args) {
        /*int count = 200;
        CountDownLatch latch = new CountDownLatch(count);
        //long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            Object obj = LazyThree.getInstance();
            System.out.println(System.currentTimeMillis() + ":" + obj);
            new Thread() {
                @Override
                public void run() {
                    try {
                        latch.await();
                        //Hungry.getInstance();
                        Object obj = LazyThree.getInstance();
                        System.out.println(System.currentTimeMillis() + ":" + obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            latch.countDown();
        }*/
        //long end = System.currentTimeMillis();
        //System.out.println("总耗时：" + (end - start));

        //RegisterEnum.INSTANCE.getInstance();

        Seriable s1 = null;
        Seriable s2 = Seriable.getInstance();

        try {
            FileOutputStream fos = new FileOutputStream("Seriable.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("Seriable.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s1 = (Seriable) ois.readObject();
            ois.close();
            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s1 == s2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
