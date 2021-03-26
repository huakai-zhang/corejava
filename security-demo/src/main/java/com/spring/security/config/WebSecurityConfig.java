package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * WebSecurityConfigurerAdapter主要作用于用户的登录(form login,Basic auth)，不用设置拦截oauth要开放的资源
 * 如果同时设置了对某一资源的访问控制，会以ResourceServerConfigurerAdapter设置的为准
 * 因为ResourceServerConfigurerAdapter优先级更高，他会优先处理，而WebSecurityConfigurerAdapter会失效
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
//    /**
//     * 定义用户信息服务（查询用户信息）
//     * @return
//     */
//    @Override
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
//        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
//        return manager;
//    }

    /**
     * 密码编码器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    /**
     * 安全拦截机制（最重要）
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1")
                .antMatchers("/r/r2").hasAuthority("p2")
                // 所有/r/**的请求必须认证通过
                .antMatchers("/r/**").authenticated()
                // 除了/r/**，其他请求可以访问
                .anyRequest().permitAll()
                .and()
                // 允许表单登录
                .formLogin()
                // 自定义登录成功的页面地址
                .successForwardUrl("/login-success");*/
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/r/**").authenticated()
                .anyRequest().permitAll() //(6)
                .and()
                .formLogin()
                .loginPage("/login-view")
                .loginProcessingUrl("/login")
                .successForwardUrl("/login-success")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login-view?logout");
    }
}
