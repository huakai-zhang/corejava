package com.spring;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author 春阳
 * @date 2021-01-25 19:41
 */
public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<SpiServer> loadedParsers = ServiceLoader.load(SpiServer.class);
        Iterator<SpiServer> iterator = loadedParsers.iterator();
        while (iterator.hasNext()){
            SpiServer SpiServer = iterator.next();
            SpiServer.sayHello("xiaoxiao");
        }
    }
}