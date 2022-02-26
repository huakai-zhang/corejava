package com.spring.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 春阳
 * @date 2021-02-04 11:50
 */
@Configuration
@ComponentScan(basePackages = "com.spring.service")
public class SpringConfig {

    @Bean
    public HuaKaiSpringServer huaKaiSpringServer() {
        return new HuaKaiSpringServer(8080);
    }

}
