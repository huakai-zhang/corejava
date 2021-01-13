package com.spring.helloservice.controller;

import com.spring.helloservice.domain.User;
import com.spring.helloservice.serivce.HelloService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 春阳
 * @date 2021-01-13 13:44
 */
@RestController
public class RefactorHelloController implements HelloService {
    @Override
    public String hello(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    @Override
    public User hello(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        return new User(name, age);
    }

    // RequestBody 必须添加
    @Override
    public String hello(@RequestBody User user) {
        return "Hello " + user.getName() + ", " + user.getAge();
    }
}
