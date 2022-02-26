package com.spring.helloservice.controller;

import com.spring.helloservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @author 春阳
 * @date 2021-01-11 17:33
 */
@RestController
public class ProHelloController {

    @GetMapping("/hello-one")
    public String hello(@RequestParam String name)   {
        return "Hello " + name;
    }

    @GetMapping("/hello-two")
    public User hello(@RequestParam String name, @RequestParam Integer age)   {
        return new User(name, age);
    }

    @PostMapping("/hello-three")
    public String hello(@RequestBody User user)   {
        return "Hello " + user.getName() + ", " + user.getAge();
    }
}
