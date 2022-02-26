package com.nio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/3
 */
public class MapperBuffer {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("E:\\info.txt", "rw");
        FileChannel fc = raf.getChannel();

        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        // 在内存的修改，会直接持久到硬盘文件中
        mbb.put(0, (byte)97);
        mbb.put(1023, (byte)122);

        raf.close();
    }
}
