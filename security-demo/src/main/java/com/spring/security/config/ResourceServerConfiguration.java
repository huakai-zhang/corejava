package com.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author 春阳
 * @date 2021-03-26 14:37
 * ResourceServerConfigurerAdapter用于保护oauth要开放的资源(哪些需要token验证后才能访问)
 * 主要作用于client端以及token的认证(Bearer auth)。
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/scope/**").access("#oauth2.hasScope('read')");
    }
}
