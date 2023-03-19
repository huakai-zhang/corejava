package com.spring.config;

import com.spring.config.datasource.DynamicDataSourceContextHolder;
import com.spring.config.datasource.MultipleDB;
import com.spring.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author 春阳
 * @date 2021-03-22 16:56
 */
@Component
@Aspect
public class LogAspect {

    @Pointcut("execution(* com.spring.service.*.*(..))")
    public void demo(){}

    /*@Pointcut("@annotation( com.spring.config.datasource.MultipleDB)")
    public void doPointcut() {}*/

    @Before("demo()")
    public void before(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + " 方法开始执行...");
    }

    /*@Around("doPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MultipleDB db = getDataSource(point);
        Long id = null;
        Object[] args = point.getArgs();
        // 获取参数中关于db的信息
        for (Object arg : args) {
            *//*if (arg instanceof User) {
                id = ((User) arg).getId();
            }*//*
            if (arg instanceof Long) {
                id = (Long) arg;
                break;
            }
        }
        if (!Objects.isNull(db) && id != null) {
            String[] dbList = {"1:db1", "2:db2"};
            // 根据方法中的dbid，与配置的id关联对应数据源
            for (String multipleDB : dbList) {
                Long dbId = Long.parseLong(multipleDB.split(":")[0]);
                String dbName = multipleDB.split(":")[1];
                if (id.equals(dbId)) {
                    DynamicDataSourceContextHolder.setDataSourceType(dbName);
                    break;
                }
            }
        }

        try {
            return point.proceed();
        } finally {
            // 在方法执行之后，销毁数据源
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    *//**
     * 获取@MultipleDB注解
     *//*
    public MultipleDB getDataSource(ProceedingJoinPoint point) {
        // 获取当前访问的Class
        Class<?> clazz = point.getTarget().getClass();
        // 判断是否存在 @MultipleDB 注解
        if (clazz.isAnnotationPresent(MultipleDB.class)) {
            // 获取注解
            return clazz.getAnnotation(MultipleDB.class);
        }

        Method method = ((MethodSignature) point.getSignature()).getMethod();
        return method.getAnnotation(MultipleDB.class);
    }*/
}
