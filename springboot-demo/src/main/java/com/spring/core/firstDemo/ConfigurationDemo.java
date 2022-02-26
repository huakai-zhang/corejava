package com.spring.core.firstDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 春阳
 * @date 2020-12-21 14:53
 */
@Configuration
public class ConfigurationDemo {
    @Bean
    public DemoClass demoClass() {
        return new DemoClass();
    }
}
