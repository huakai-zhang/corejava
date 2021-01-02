package com.juc.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/2 下午2:27
 */
public class ThreadLocalHash {
    private final int threadLocalHashCode = nextHashCode();

    private static AtomicInteger nextHashCode =
            new AtomicInteger();

    private static final int HASH_INCREMENT = 0x61c88647;

    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    public static void main(String[] args) {
        magicHash(16);
        magicHash(32);
    }

    private static void magicHash(int size) {
        String hashStr = "";
        for (int i = 0; i < size; i++) {
            ThreadLocalHash hash = new ThreadLocalHash();
            System.out.println(hash.threadLocalHashCode);
            hashStr += ((hash.threadLocalHashCode & (size-1)) + " ");
        }
        System.out.println(hashStr);
    }
}
