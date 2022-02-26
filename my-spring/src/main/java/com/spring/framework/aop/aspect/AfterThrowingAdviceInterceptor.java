package com.spring.framework.aop.aspect;

import com.spring.framework.aop.intercept.MethodInterceptor;
import com.spring.framework.aop.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author 花开不合阳春暮
 * @date 2020/12/7 下午10:09
 */
public class AfterThrowingAdviceInterceptor extends AbstractAspectAdvice implements Advice, MethodInterceptor {

    private String throwingName;

    public AfterThrowingAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        }catch (Throwable e){
            invokeAdviceMethod(mi,null,e.getCause());
            throw e;
        }
    }

    public void setThrowName(String throwName){
        this.throwingName = throwName;
    }
}
