package com.spring.core.secondDemo.other;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 春阳
 * @date 2020-12-21 15:47
 */
@Configuration
public class OtherConfig {
    @Bean
    public OtherDemo otherDemo() {
        return new OtherDemo();
    }
}
