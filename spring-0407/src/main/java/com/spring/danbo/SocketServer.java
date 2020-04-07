package com.spring.danbo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/7
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            // 启动一个服务
            serverSocket = new ServerSocket(8888);
            while (true) {
                // 等待一个接受请求
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                        while (true) {
                            // 客户端发送过来的消息
                            String clientData = reader.readLine();
                            if (clientData == null) {
                                break;
                            }
                            System.out.println("服务端收到的数据：" + clientData);
                            writer.println("你好");
                            writer.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {

        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
