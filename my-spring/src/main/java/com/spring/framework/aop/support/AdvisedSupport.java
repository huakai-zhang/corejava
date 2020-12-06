package com.spring.framework.aop.support;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author 花开不合阳春暮
 * @date 2020/11/29 上午11:06
 */
public class AdvisedSupport {

    private Class<?> targetClass;

    private Object target;

    public Class<?> getTargetClass() {
        return this.targetClass;
    }

    public Object getTarget() {
        return null;
    }

    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) {
        return null;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
