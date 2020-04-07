package com.spring.danbo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/7
 */
public class SocketClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            // 读取服务端信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 往服务端写数据
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("Hello");
            while (true) {
                String serverData = reader.readLine();
                if (serverData == null) {
                    break;
                }
                System.out.println("客户端收到数据：" + serverData);
            }
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
