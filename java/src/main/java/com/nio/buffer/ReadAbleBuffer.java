package com.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/3
 */
public class ReadAbleBuffer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }
        // 创建只读缓冲区
        ByteBuffer readonly = buffer.asReadOnlyBuffer();

        // 改变子缓冲区的内容
        for (int i = 0; i < buffer.capacity(); ++i) {
            byte b = buffer.get(i);
            b *= 10;
            buffer.put(i, b);
        }

        readonly.position(0);
        readonly.limit(buffer.capacity());

        while (readonly.remaining() > 0) {
            System.out.println(readonly.get());
        }
    }
}
