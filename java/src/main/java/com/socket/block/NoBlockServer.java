package com.socket.block;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 春阳
 * @date 2020-12-15 11:50
 */
public class NoBlockServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
            while(true) {
                // 连接阻塞
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        System.out.println("连接成功");
                        // IO 阻塞
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        System.out.println(in.readLine());
                        PrintWriter os = new PrintWriter(socket.getOutputStream());
                        os.println("哈哈，你好");
                        os.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
