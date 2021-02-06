package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author 春阳
 * @date 2021-02-04 11:50
 */
@Configuration
@ComponentScan(basePackages = "com.spring")
public class SpringConfig {

    @Bean
    public HuaKaiSpringServer huaKaiSpringServer() {
        return new HuaKaiSpringServer(8081);
    }
}
