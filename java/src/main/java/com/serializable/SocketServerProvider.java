package com.serializable;

import com.spring.model.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 春阳
 * @date 2020-12-18 14:46
 */
public class SocketServerProvider {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            ObjectInputStream objectInputStream= new ObjectInputStream(socket.getInputStream());
            User user=(User)objectInputStream.readObject();
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
