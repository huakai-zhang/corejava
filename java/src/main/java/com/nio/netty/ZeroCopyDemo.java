package com.nio.netty;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author 春阳
 * @date 2021-01-05 16:50
 */
public class ZeroCopyDemo {

    /**
     * filechannel进行文件复制（零拷贝）
     *
     * @param fromFile 源文件
     * @param toFile   目标文件
     */
    public static void fileCopyWithFileChannel(File fromFile, File toFile) {
        try (// 得到fileInputStream的文件通道
             FileChannel fileChannelInput = new FileInputStream(fromFile).getChannel();
             // 得到fileOutputStream的文件通道
             FileChannel fileChannelOutput = new FileOutputStream(toFile).getChannel()) {

            //将fileChannelInput通道的数据，写入到fileChannelOutput通道
            fileChannelInput.transferTo(0, fileChannelInput.size(), fileChannelOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static final int BUFFER_SIZE = 1024;
    /**
     * BufferedInputStream进行文件复制（用作对比实验）
     *
     * @param fromFile 源文件
     * @param toFile   目标文件
     */
    public static void bufferedCopy(File fromFile,File toFile) throws IOException {
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fromFile));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(toFile))){
            byte[] buf = new byte[BUFFER_SIZE];
            while ((bis.read(buf)) != -1) {
                bos.write(buf);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        //fileCopyWithFileChannel(new File("E:\\迅雷下载\\CentOS-7-x86_64-DVD-2003\\CentOS-7-x86_64-DVD-2003.iso"),
                //new File("D:\\CentOS-7-x86_64-DVD-2003.iso"));
        // 8333
        bufferedCopy(new File("E:\\迅雷下载\\CentOS-7-x86_64-DVD-2003\\CentOS-7-x86_64-DVD-2003.iso"),
                new File("D:\\CentOS-7-x86_64-DVD-2003.iso"));
        // 22677
        System.out.println(System.currentTimeMillis() - startTime);

    }
}
