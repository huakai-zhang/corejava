package com.spring.core.thirdDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 春阳
 * @date 2020-12-21 15:59
 */
@SpringBootApplication
@EnableDefineService
public class ThirdDemoMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext ca= SpringApplication.run(ThirdDemoMain.class,args);

        System.out.println(ca.getBean(CacheService.class));
        System.out.println(ca.getBean(LoggerService.class));
    }
}
