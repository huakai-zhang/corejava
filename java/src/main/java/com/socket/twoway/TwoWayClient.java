package com.socket.twoway;

import java.io.*;
import java.net.Socket;

/**
 * @author 春阳
 * @date 2020-12-15 12:14
 */
public class TwoWayClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter os = new PrintWriter(socket.getOutputStream());

            new Thread(() -> {
                try {
                    String line;
                    while (!(line = is.readLine()).equals("bye")) {
                        System.out.println("Server: " + line);
                    }
                } catch (Exception e) {
                    System.out.println("Error. " + e);
                }
            }).start();

            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            String outLine;
            while (!(outLine = sin.readLine()).equals("bye")) {
                os.println(outLine);
                os.flush();
            }
            os.println(outLine);
            os.flush();

            os.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error. " + e);
        }
    }
}
