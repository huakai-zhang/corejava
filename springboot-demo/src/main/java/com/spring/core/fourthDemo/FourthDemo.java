package com.spring.core.fourthDemo;

import com.spring.core.fourthDemo.core.SpringConfig;
import com.spring.core.fourthDemo.core.SpringCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 春阳
 * @date 2020-12-21 16:32
 */
@SpringBootApplication
public class FourthDemo {
    public static void main(String[] args) {
        ConfigurableApplicationContext ca= SpringApplication.run(FourthDemo.class,args);

        System.out.println(ca.getBean(SpringCore.class).study());
    }
}
