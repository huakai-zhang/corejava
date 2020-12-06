package com.spring.framework.aop.intercept;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author 花开不合阳春暮
 * @date 2020/11/29 上午11:15
 */
public class MethodInvocation {

    public MethodInvocation(
            Object proxy, Object target, Method method, Object[] arguments,
            Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers) {

//        this.proxy = proxy;
//        this.target = target;
//        this.targetClass = targetClass;
//        this.method = BridgeMethodResolver.findBridgedMethod(method);
//        this.arguments = AopProxyUtils.adaptArgumentsIfNecessary(method, arguments);
//        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }

    public Object proceed() throws Throwable {
        return null;
    }

}
