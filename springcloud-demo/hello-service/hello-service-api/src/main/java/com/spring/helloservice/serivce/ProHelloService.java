package com.spring.helloservice.serivce;

import com.spring.helloservice.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

/**
 * @author 春阳
 * @date 2021-01-13 13:58
 */
@FeignClient("hello-service")
@Primary
public interface ProHelloService {

    @GetMapping("/hello-one")
    String hello(@RequestParam("name") String name);

    @GetMapping("/hello-two")
    User hello(@RequestParam("name") String name, @RequestParam("age") Integer age);

    @PostMapping("/hello-three")
    String hello(@RequestBody User user);

}
