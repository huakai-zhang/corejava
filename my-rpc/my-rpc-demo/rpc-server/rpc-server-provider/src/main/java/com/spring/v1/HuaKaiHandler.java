package com.spring.v1;

import com.spring.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author 春阳
 * @date 2021-02-03 13:48
 */
public class HuaKaiHandler implements Runnable {

    private Socket socket;
    private Object server;

    public HuaKaiHandler(Object server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest) ois.readObject();

            Object object = invoke(request);

            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(object);
            // TODO
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest request) {
        Class<?> clazz = server.getClass();

        /*Object[] args = request.getParameters();
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }*/

        try {
            Method method = clazz.getMethod(request.getMethodName(), request.getParameterTypes());
            return method.invoke(server, request.getParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
