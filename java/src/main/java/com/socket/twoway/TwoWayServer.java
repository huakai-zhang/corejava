package com.socket.twoway;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 春阳
 * @date 2020-12-15 12:10
 */
public class TwoWayServer {
    public static void main(String[] args) {
        try {
            // 创建一个 ServerSocket 监听客户请求
            ServerSocket server = new ServerSocket(8888);
            Socket socket = null;
            try {
                // 使用 accept()阻塞等待客户请求
                socket = server.accept();
            } catch (Exception e) {
                System.out.println("Error. " + e);
            }

            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            new Thread(() -> {
                try {
                    // 从标准输入读入一字符串，如果该字符串为 bye，则停止循环
                    String line;
                    while (!(line = is.readLine()).equals("bye")) {
                        System.out.println("Client: " + line);
                    }
                    System.out.println("Client 断开连接");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            // 由Socket对象得到输出流，并构造PrintWriter对象
            PrintWriter os = new PrintWriter(socket.getOutputStream());

            // 由系统标准输入设备构造BufferedReader对象
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            String outLine;
            while (!(outLine = sin.readLine()).equals("bye")) {
                // 向客户端输出该字符串
                os.println(outLine);
                // 刷新输出流，使Client马上收到该字符串
                os.flush();
            }
            System.out.println("服务断开");
            os.close();
            is.close();
            socket.close();
            server.close();
        } catch (Exception e) {
            System.out.println("Error. " + e);
        }
    }
}