package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/1 下午1:27
 */
@Configuration
@ComponentScan(basePackages = "com.spring")
public class SpringConfig {

    @Bean("springRpcServer")
    public SpringRpcServer springRpcServer() {
        return new SpringRpcServer(8080);
    }

}
