package com.spring.cyclicdependence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 春阳
 * @date 2021-06-15 15:10
 */
@Configuration
public class CyclicDependenceConfig {
    @Bean
    public A a() {
        return new A();
    }

    @Bean
    public B b() {
        return new B();
    }
}
