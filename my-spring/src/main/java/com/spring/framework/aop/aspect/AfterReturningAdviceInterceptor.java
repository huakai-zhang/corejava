package com.spring.framework.aop.aspect;

import com.spring.framework.aop.intercept.MethodInterceptor;
import com.spring.framework.aop.intercept.MethodInvocation;
import com.spring.framework.aop.support.AdvisedSupport;

import java.lang.reflect.Method;

/**
 * @author 花开不合阳春暮
 * @date 2020/12/7 下午10:08
 */
public class AfterReturningAdviceInterceptor extends AbstractAspectAdvice implements Advice, MethodInterceptor {

    private JoinPoint joinPoint;

    public AfterReturningAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object retVal = mi.proceed();
        this.joinPoint = mi;
        this.afterReturning(retVal,mi.getMethod(),mi.getArguments(),mi.getThis());
        return retVal;
    }

    private void afterReturning(Object retVal, Method method, Object[] arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.joinPoint,retVal,null);
    }
}
