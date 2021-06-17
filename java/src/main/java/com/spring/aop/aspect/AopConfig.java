package com.spring.aop.aspect;

import com.spring.aop.service.AuthService;
import com.spring.aop.service.AuthServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 春阳
 * @date 2021-06-15 17:15
 */
@Configuration
public class AopConfig {

    @Bean
    public AuthService authService() {
        return new AuthServiceImpl();
    }

}
