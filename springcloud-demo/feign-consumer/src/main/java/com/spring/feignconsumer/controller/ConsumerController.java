package com.spring.feignconsumer.controller;

import com.spring.feignconsumer.domain.User;
import com.spring.feignconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 春阳
 * @date 2021-01-13 11:42
 */
@RestController
public class ConsumerController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/feign-consumer")
    public String helloConsumer() {
        return helloService.hello();
    }

    @GetMapping("/feign-consumer2")
    public String helloConsumer2() {
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello()).append("\r\n");
        sb.append(helloService.hello("XiaoXiao")).append("\r\n");
        sb.append(helloService.hello("XiaoXiao", 18)).append("\r\n");
        sb.append(helloService.hello(new User("XiaoXiao", 18))).append("\r\n");
        return sb.toString();
    }

}
