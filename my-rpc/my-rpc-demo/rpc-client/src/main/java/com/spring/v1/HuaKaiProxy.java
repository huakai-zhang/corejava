package com.spring.v1;

import com.spring.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author 春阳
 * @date 2021-02-03 14:02
 */
public class HuaKaiProxy implements InvocationHandler {

    private String host;
    private Integer port;

    public HuaKaiProxy(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public <T> T getInstance(Class<T> interfaceClass) {
        // 生成代理类
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取方法名和参数
        RpcRequest request = new RpcRequest();
        request.setMethodName(method.getName());
        request.setParameterTypes(method.getParameterTypes());
        request.setParameters(args);
        // 建立远程通信
        Socket socket = new Socket(host, port);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        // 发送序列化消息
        objectOutputStream.writeObject(request);
        objectOutputStream.flush();

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        return objectInputStream.readObject();
    }
}
