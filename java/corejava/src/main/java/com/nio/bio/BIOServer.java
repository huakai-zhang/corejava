package com.nio.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {

    public int count = 0;

    ServerSocket server;

    public BIOServer(int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("BIO服务已启动，监听端口是：" + port);
    }

    public void listener() throws IOException {
        // 循环监听
        while (true) {
            // 虽然写了一个死循环，如果一直没有客户端连接的话，这里一直不会往下执行
            // 等待客户端连接，阻塞方法
            Socket client = server.accept();
            // 拿到输入流，也就是乡村公路
            InputStream is = client.getInputStream();

            // 缓冲区
            byte[] buff = new byte[1024];
            int len = is.read(buff);
            // 只要一直有数据写入，len就会一直大于0
            if (len > 0) {
                String msg = new String(buff, 0, len);
                count++;
                System.out.println("收到第" + count + "条数据：" + msg);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new BIOServer(8080).listener();
    }
}
