package com.spring.controller;

import com.spring.service.NacosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author 春阳
 * @date 2021-02-19 15:47
 */
@RestController
@RefreshScope
public class NacosController {

    @Value("${user.name}")
    private String userName;

    @Value("${user.age}")
    private String userAge;

    @Autowired
    private NacosService nacosService;

    @GetMapping("/nacos")
    public String nacos() throws InterruptedException {
        int time = new Random().nextInt(1000);
        System.out.println("睡眠时间：" + time);
        Thread.sleep(time);
        return nacosService.nacos() + "Name: " + userName + ", Age: " + userAge;
    }

}