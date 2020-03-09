package com.spring.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

public class LogAspect {

    private final static Logger logger = Logger.getLogger(LogAspect.class);

    public void before(JoinPoint joinPoint) {
        System.out.println(111111);
        logger.info("调用方法之前执行" + joinPoint);
    }

    public void after(JoinPoint joinPoint) {
        logger.info("调用方法之后执行" + joinPoint);
    }

    public void afterReturn(JoinPoint joinPoint) {
        logger.info("调用获得返回值之后执行" + joinPoint);
    }

    public void afterThrow(JoinPoint joinPoint) {
        logger.info("抛出异常之后执行" + joinPoint);
    }
}
