package com.spring.feignconsumer.service;

import com.spring.feignconsumer.config.DisableHystrixConfiguration;
import com.spring.feignconsumer.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author 春阳
 * @date 2021-01-13 11:40
 */
@FeignClient(name="hello-service", fallback = HelloServiceFallback.class) //, configuration = DisableHystrixConfiguration.class)
public interface HelloService {

    @RequestMapping("/hello")
    String hello();

    @GetMapping("/hello1")
    String hello(@RequestParam("name") String name);

    @GetMapping("/hello2")
    User hello(@RequestParam("name") String name, @RequestParam("age") Integer age);

    @PostMapping("/hello3")
    String hello(@RequestBody User user);
}
