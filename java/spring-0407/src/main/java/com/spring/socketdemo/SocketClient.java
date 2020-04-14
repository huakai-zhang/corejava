package com.spring.socketdemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/7
 */
public class SocketClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("Hello");
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
