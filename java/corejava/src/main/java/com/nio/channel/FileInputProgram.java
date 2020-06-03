package com.nio.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/3
 */
public class FileInputProgram {
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream("E:\\info.txt");

        // 获取通道
        FileChannel fc = fin.getChannel();
        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 读取数据到缓冲区
        fc.read(buffer);

        buffer.flip();

        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            System.out.println((char) b);
        }

        fin.close();
    }
}
