package com.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/3
 */
public class BufferWrap {
    public void myMethod() {
        // 分配指定大小的缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(10);

        // 包装一个现有数组
        byte array[] = new byte[10];
        ByteBuffer buffer2 = ByteBuffer.wrap(array);
    }
}
