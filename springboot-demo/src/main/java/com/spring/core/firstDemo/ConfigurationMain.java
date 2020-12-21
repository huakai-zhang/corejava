package com.spring.core.firstDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 春阳
 * @date 2020-12-21 14:54
 */
//@ComponentScan(basePackages = "com.spring.core.firstDemo")
public class ConfigurationMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationDemo.class);
        String[] defNames = context.getBeanDefinitionNames();
        for(int i = 0;i < defNames.length; i++){
            System.out.println(defNames[i]);
        }
    }
}
