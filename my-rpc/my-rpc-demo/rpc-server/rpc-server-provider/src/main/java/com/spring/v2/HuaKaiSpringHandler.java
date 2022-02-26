package com.spring.v2;

import com.spring.RpcRequest;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @author 春阳
 * @date 2021-02-03 15:24
 */
public class HuaKaiSpringHandler implements Runnable {

    private Socket socket;

    private Map<String, Object> handleMap;

    public HuaKaiSpringHandler(Socket socket, Map<String, Object> handleMap) {
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

    private Object invoke(RpcRequest request) throws Exception {
        String serviceName = request.getClassName();
        String version = request.getVersion();
        // 增加版本号
        if(!StringUtils.isEmpty(version)) {
            serviceName += "-" + version;
        }
        Object object = handleMap.get(serviceName);

        if (object == null) {
            throw new RuntimeException("service not found: " + serviceName);
        }

        Object[] parameters = request.getParameters();
        Method method;
        if (parameters != null) {
            /*Class<?>[] parameterTypes = new Class[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                parameterTypes[i] = parameters[i].getClass();
            }*/
            Class<?> clazz = Class.forName(request.getClassName());
            method = clazz.getMethod(request.getMethodName(), request.getParameterTypes());
        } else {
            Class<?> clazz = Class.forName(request.getClassName());
            method = clazz.getMethod(request.getMethodName());
        }
        return method.invoke(object, parameters);
    }
}
