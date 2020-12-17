package com.socket.block;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 春阳
 * @date 2020-12-15 11:50
 */
public class BlockServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        BufferedReader in = null;
        try {
            serverSocket = new ServerSocket(8888);
            while(true) {
                // 连接阻塞
                Socket socket = serverSocket.accept();
                System.out.println("连接成功");
                // IO 阻塞
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.println(in.readLine());
                PrintWriter os = new PrintWriter(socket.getOutputStream());
                os.println("哈哈，你好");
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
