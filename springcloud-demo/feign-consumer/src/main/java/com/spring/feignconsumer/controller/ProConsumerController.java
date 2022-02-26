package com.spring.feignconsumer.controller;

import com.spring.helloservice.domain.User;
import com.spring.helloservice.serivce.ProHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 春阳
 * @date 2021-01-13 14:01
 */
@RestController
@RequestMapping("/pro")
public class ProConsumerController {

    private ProHelloService proHelloService;

    @Autowired
    public ProConsumerController(ProHelloService proHelloService) {
        this.proHelloService = proHelloService;
    }

    @GetMapping("/feign-consumer2")
    public String helloConsumer2() {
        StringBuilder sb = new StringBuilder();
        sb.append(proHelloService.hello("XiaoXiao")).append("\r\n");
        sb.append(proHelloService.hello("XiaoXiao", 18)).append("\r\n");
        sb.append(proHelloService.hello(new User("XiaoXiao", 18))).append("\r\n");
        return sb.toString();
    }

}
