package com.spring.framework.aop;

/**
 * @author 花开不合阳春暮
 * @date 2020/11/29 上午11:01
 */
public interface AopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);

}
