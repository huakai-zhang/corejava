package com.spring.core.fourthDemo.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public SpringCore springCore(){
        return new SpringCore();
    }
}
