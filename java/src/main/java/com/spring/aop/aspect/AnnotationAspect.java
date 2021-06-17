package com.spring.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// 声明这个类是被SpringIOC容器来管理的，如果不声明就无法做到自动织入
@Component
// 这个类被声明为是一个需要动态织入到我们的虚拟切面中的类
@Aspect
public class AnnotationAspect {

    private final static Logger logger = Logger.getLogger(AnnotationAspect.class);

    // 声明切点
    // 因为要利用反射机制去读取这个切面中的所有的注解信息
    @Pointcut("execution(* com.spring.aop.service..*(..))")
    public void pointcutConfig(){}

    @Before("pointcutConfig()")
    public void before(JoinPoint joinPoint) {
        System.out.println("调用方法之前执行" + joinPoint);
        logger.info("调用方法之前执行" + joinPoint);
    }

    @After("pointcutConfig()")
    public void after(JoinPoint joinPoint) {
        System.out.println("调用方法之后执行" + joinPoint);
        logger.info("调用方法之后执行" + joinPoint);
    }

    @AfterReturning("pointcutConfig()")
    public void afterReturn(JoinPoint joinPoint) {
        System.out.println("调用获得返回值之后执行" + joinPoint);
        logger.info("调用获得返回值之后执行" + joinPoint);
    }

    @AfterThrowing("pointcutConfig()")
    public void afterThrow(JoinPoint joinPoint) {
        System.out.println("切点的参数" + Arrays.toString(joinPoint.getArgs()));
        System.out.println("切点的方法" + joinPoint.getKind());
        System.out.println(joinPoint.getSignature());
        // 生成以后的代理对象
        System.out.println(joinPoint.getTarget());
        // 当前类的本身（通过反射机制去调用）
        System.out.println(joinPoint.getThis());

        logger.info("抛出异常之后执行" + joinPoint);
    }

    //@Around("pointcutConfig()")
    public void around(JoinPoint joinPoint) {
        System.out.println("环绕通知" + joinPoint);
    }
}
