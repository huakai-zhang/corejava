package com.spring.helloservice.serivce;

import com.spring.helloservice.domain.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author 春阳
 * @date 2021-01-13 11:40
 */
@RequestMapping("/refactor")
public interface HelloService {

    @GetMapping("/hello4")
    String hello(@RequestParam("name") String name);

    @GetMapping("/hello5")
    User hello(@RequestParam("name") String name, @RequestParam("age") Integer age);

    @PostMapping("/hello6")
    String hello(@RequestBody User user);
}
