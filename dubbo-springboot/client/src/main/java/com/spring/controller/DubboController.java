package com.spring.controller;

import com.spring.service.ISayHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/23 上午11:47
 */
@RestController
public class DubboController {

    @Reference(timeout = 3000, check = false)//cluster = "failfast", mock = "com.spring.mock.SayHelloServiceMock")// loadbalance = "random",
    ISayHelloService sayHelloService;

    @GetMapping("/sayHello")
    public String sayHello() {
        //RpcContext.getContext().setAttachment("dubboApplication","dubbo-springboot-client");
        return sayHelloService.sayHello();
    }

}
