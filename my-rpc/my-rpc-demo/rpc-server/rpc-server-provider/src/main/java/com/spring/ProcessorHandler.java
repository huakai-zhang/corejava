package com.spring;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/1 下午12:25
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;

    private Map<String, Object> handleMap;
    //private Object server;

    /*public ProcessorHandler(Socket socket, Object server) {
        this.server = server;
        this.socket = socket;
    }*/

    public ProcessorHandler(Socket socket, Map<String, Object> handleMap) {
        this.socket = socket;
        this.handleMap = handleMap;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();

            Object result = invoke(rpcRequest);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String serviceName = request.getClassName();
        String version = request.getVersion();
        // 增加版本号
        if(!StringUtils.isEmpty(version)) {
            serviceName += "-" + version;
        }

        Object server = handleMap.get(serviceName);

        if (server == null) {
            throw new RuntimeException("service not found: " + serviceName);
        }

        // 拿到客户端的请求参数
        Object[] args = request.getParameters();
        Method method;
        if (args != null) {
            // 获得每个参数类型
            Class<?>[] types = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                types[i] = args[i].getClass();
            }
            // 根据请求的类进行加载
            Class clazz = Class.forName(request.getClassName());
            method = clazz.getMethod(request.getMethodName(), types);
        } else {
            // 根据请求的类进行加载
            Class clazz = Class.forName(request.getClassName());
            method = clazz.getMethod(request.getMethodName());
        }
        Object result = method.invoke(server, args);
        return result;
    }
}
