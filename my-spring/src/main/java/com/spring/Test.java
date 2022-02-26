package com.spring;

import com.spring.demo.controller.HelloController;
import com.spring.framework.context.ApplicationContext;

/**
 * @author 花开不合阳春暮
 * @date 2020/11/28 下午3:00
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext("classpath:application.properties");
        //Object object = applicationContext.getBean("helloController");
        Object object = applicationContext.getBean(HelloController.class);
        System.out.println(object);
    }
}
