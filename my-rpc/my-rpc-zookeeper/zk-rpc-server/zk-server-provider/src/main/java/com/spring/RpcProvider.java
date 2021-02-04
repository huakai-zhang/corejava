package com.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 春阳
 * @date 2021-02-04 11:46
 */
public class RpcProvider {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        applicationContext.start();
    }
}
