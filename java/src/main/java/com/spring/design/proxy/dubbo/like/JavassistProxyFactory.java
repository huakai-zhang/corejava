package com.spring.design.proxy.dubbo.like;

import java.lang.reflect.InvocationHandler;

/**
 * @author 春阳
 * @date 2021-01-29 19:00
 */
public class JavassistProxyFactory implements ProxyFactory {
    @Override
    public <T> T getProxy(Object target, InvocationHandler handler) throws Throwable {
        return (T) ProxyGenerator.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass(), handler);
    }

    @Override
    public <T> T getProxy(Object target) throws Throwable {
        return (T) ProxyGenerator.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass());
    }
}
