package com.spring.feignconsumer.controller;

import com.spring.feignconsumer.service.RefactorHelloService;
import com.spring.helloservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 春阳
 * @date 2021-01-13 11:42
 */
@RestController
public class RefactorConsumerController {

    @Autowired
    private RefactorHelloService refactorHelloService;

    @GetMapping("/feign-consumer3")
    public String helloConsumer() {
        StringBuilder sb = new StringBuilder();
        sb.append(refactorHelloService.hello("XiaoXiao")).append("\r\n");
        sb.append(refactorHelloService.hello("XiaoXiao", 18)).append("\r\n");
        sb.append(refactorHelloService.hello(new User("XiaoXiao", 18))).append("\r\n");
        return sb.toString();
    }

}