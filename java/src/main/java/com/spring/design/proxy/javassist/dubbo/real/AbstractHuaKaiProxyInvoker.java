package com.spring.design.proxy.javassist.dubbo.real;

/**
 * @author 春阳
 * @date 2021-01-29 14:38
 */
public abstract class AbstractHuaKaiProxyInvoker<T> {
    public AbstractHuaKaiProxyInvoker() {
    }

    protected abstract void doInvoke(String methodName);
}
