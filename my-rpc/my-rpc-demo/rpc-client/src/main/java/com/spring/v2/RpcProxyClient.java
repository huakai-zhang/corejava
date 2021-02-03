package com.spring.v2;

import java.lang.reflect.Proxy;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/1 下午12:48
 */
public class RpcProxyClient {

    public <T> T clientProxy(final Class<T> interfaceCls, final String host, final int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class<?>[]{interfaceCls}, new RemoteInvocationHandler(host, port));
    }

}
