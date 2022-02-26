package com.spring.feignconsumer.service;

import com.spring.feignconsumer.domain.User;
import org.springframework.stereotype.Component;

/**
 * @author 春阳
 * @date 2021-01-13 15:10
 */
@Component
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello(String name) {
        return "error";
    }

    @Override
    public User hello(String name, Integer age) {
        return new User("未知", 0);
    }

    @Override
    public String hello(User user) {
        return "error";
    }
}
