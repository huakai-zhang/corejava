package com.jvm;

import sun.misc.VM;

import java.nio.ByteBuffer;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/30
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory:" + (VM.maxDirectMemory() / 1024 / 1023) + "MB");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
