package com.spring.design.proxy.dubbo.like;

import java.lang.reflect.InvocationHandler;

/**
 * @author 春阳
 * @date 2021-01-29 19:00
 */
public interface ProxyFactory {
    <T> T getProxy(Object target, InvocationHandler handler) throws Throwable;

    <T> T getProxy(Object target) throws Throwable;
}
