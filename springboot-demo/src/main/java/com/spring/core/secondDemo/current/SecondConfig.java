package com.spring.core.secondDemo.current;

import com.spring.core.secondDemo.other.OtherConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 春阳
 * @date 2020-12-21 15:45
 */
@Import(OtherConfig.class)
@Configuration
public class SecondConfig {
    @Bean
    public SecondDemo secondDemo() {
        return new SecondDemo();
    }
}
