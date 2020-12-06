package com.spring.framework.aop.intercept;

/**
 * @author 花开不合阳春暮
 * @date 2020/11/29 上午11:25
 */
public interface MethodInterceptor {

    Object invoke(MethodInvocation invocation) throws Throwable;

}
