package com.socket.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 春阳
 * @date 2020-12-15 11:55
 */
public class SimpleClient {
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1", 8888);
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello, 晓晓");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
