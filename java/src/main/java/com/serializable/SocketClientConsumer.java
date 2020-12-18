package com.serializable;

import com.spring.model.User;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author 春阳
 * @date 2020-12-18 14:48
 */
public class SocketClientConsumer {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            User user = new User();
            user.setId(1);
            user.setName("晓晓");
            objectOutputStream.writeObject(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
