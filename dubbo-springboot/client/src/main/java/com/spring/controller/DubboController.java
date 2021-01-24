package com.spring.controller;

import com.spring.service.ISayHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/23 上午11:47
 */
@RestController
public class DubboController {

    @Reference(timeout = 1, cluster = "failfast", mock = "com.spring.mock.SayHelloServiceMock")
    ISayHelloService sayHelloService;

    @GetMapping("/sayHello")
    public String sayHello() {
        return sayHelloService.sayHello();
    }

}
