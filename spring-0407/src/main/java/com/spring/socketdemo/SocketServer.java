package com.spring.socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            Socket socket = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(reader.readLine());
            reader.close();
        } catch (Exception e) {

        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
