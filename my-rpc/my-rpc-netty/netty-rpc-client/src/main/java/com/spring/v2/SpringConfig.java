package com.spring.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 春阳
 * @date 2021-02-04 12:18
 */
@Configuration
public class SpringConfig {

    @Bean
    public HuaKaiSpringProxy huaKaiSpringProxy() {
        return new HuaKaiSpringProxy();
    }

}
