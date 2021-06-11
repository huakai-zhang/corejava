package com.spring.model;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * @author 春阳
 * @date 2021-06-11 17:45
 */
public class MyBeanReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("我替换了原来的方法");
        return null;
    }
}
