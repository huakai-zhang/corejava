package com.spring.framework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @author 花开不合阳春暮
 * @date 2020/12/7 下午11:02
 */
public interface JoinPoint {

    Object getThis();

    Object[] getArguments();

    Method getMethod();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);

}
