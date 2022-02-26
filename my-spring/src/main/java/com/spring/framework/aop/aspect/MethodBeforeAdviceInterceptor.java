package com.spring.framework.aop.aspect;

import com.spring.framework.aop.intercept.MethodInterceptor;
import com.spring.framework.aop.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author 花开不合阳春暮
 * @date 2020/12/7 下午10:08
 */
public class MethodBeforeAdviceInterceptor extends AbstractAspectAdvice implements Advice, MethodInterceptor {

    private JoinPoint joinPoint;

    public MethodBeforeAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    private void before(Method method,Object[] args,Object target) throws Throwable{
        //传送了给织入参数
        //method.invoke(target);
        super.invokeAdviceMethod(this.joinPoint,null,null);

    }
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //从被织入的代码中才能拿到，JoinPoint
        this.joinPoint = mi;
        before(mi.getMethod(), mi.getArguments(), mi.getThis());
        return mi.proceed();
    }
}
