package com.nio.channel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/3
 */
public class FileOutputProgram {
    static private final byte message[] = {83, 111, 109, 101, 32, 98, 121, 116, 101, 115, 46};
    public static void main(String[] args) throws IOException {
        FileOutputStream fout = new FileOutputStream("E:\\info.txt");
        FileChannel fc = fout.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        for (int i = 0; i < message.length; ++i) {
            buffer.put(message[i]);
        }
        buffer.flip();
        fc.write(buffer);
        fout.close();
    }
}
