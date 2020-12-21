package com.spring.core.secondDemo.current;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 春阳
 * @date 2020-12-21 15:44
 */
public class SecondMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SecondConfig.class);
        String[] defNames = context.getBeanDefinitionNames();
        for(int i = 0;i < defNames.length; i++){
            System.out.println(defNames[i]);
        }
    }
}
