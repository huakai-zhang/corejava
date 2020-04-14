package com.spring.rmi.myself;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/9
 */
public class User_Skeleton extends Thread {

    private UserServer userServer;

    public User_Skeleton(UserServer userServer) {
        this.userServer = userServer;
    }

    // server socket
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();

            while (socket != null) {
                ObjectInputStream read = new ObjectInputStream(socket.getInputStream());
                String method = (String) read.readObject();

                if (method.equals("age")) {
                    int age = userServer.getAge();
                    ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                    os.writeInt(age);
                    os.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
