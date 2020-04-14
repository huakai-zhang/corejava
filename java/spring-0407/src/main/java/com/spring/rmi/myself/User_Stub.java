package com.spring.rmi.myself;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/9
 */
public class User_Stub extends User {

    private Socket socket;

    public User_Stub() throws IOException {
        socket = new Socket("localhost", 8888);
    }

    @Override
    public int getAge() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        os.writeObject("age");
        os.flush();

        ObjectInputStream oi = new ObjectInputStream(socket.getInputStream());
        return oi.readInt();
    }
}
