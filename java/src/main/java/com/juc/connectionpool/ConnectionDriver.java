package com.juc.connectionpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @author 花开不合阳春暮
 * @date 2021/4/18 下午12:29
 */
public class ConnectionDriver {
    public static Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class<?>[] {Connection.class}, new ConnectionHandler());
    }

    // Connection 是一个接口，这里做一个代理实现仅仅在 commit() 方法调用时休眠 100 毫秒
    static class ConnectionHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("commit")) {
                TimeUnit.MILLISECONDS.sleep(100);
            }

            return null;
        }
    }
}
