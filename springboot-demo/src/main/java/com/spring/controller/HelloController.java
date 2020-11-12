package com.spring.controller;

import com.spring.entity.User;
import com.spring.service.AsyncHandlerTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(value = "HelloController", tags = "测试API", description = "")
@Slf4j
public class HelloController {

    @Value("${dev.value}")
    private String value;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AsyncHandlerTask asyncHandlerTask;

    @GetMapping("/hello")
    public String hello() {
        //rabbitTemplate.convertAndSend("", "work", "work消息");
        log.info(value);
        return "Hello Swagger!";
    }

    @GetMapping("/get_user")
    @ApiOperation("spring 的接口")
    public User getUser(@ApiParam("这个名字会被返回") String username) {
        return new User();
    }

    @GetMapping("/task")
    @ApiOperation("task 的接口")
    public void task() {
        asyncHandlerTask.sendMessage();
    }

    @GetMapping("/redis")
    @ApiOperation("redis 5分钟 的接口")
    public void redis() {
        rabbitTemplate.convertAndSend("DZ_REDIS_EXCHANGE", "dz.redis", "10秒延时消息");
    }
}
