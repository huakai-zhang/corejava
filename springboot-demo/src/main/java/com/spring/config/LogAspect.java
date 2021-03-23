package com.spring.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 春阳
 * @date 2021-03-22 16:56
 */
@Component
@Aspect
public class LogAspect {

    @Pointcut("execution(* com.spring.service.*.*(..))")
    public void demo(){}

    @Before("demo()")
    public void before(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + " 方法开始执行...");
    }
}
