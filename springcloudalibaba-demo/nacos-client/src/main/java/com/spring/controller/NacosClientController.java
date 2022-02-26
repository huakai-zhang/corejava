package com.spring.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.spring.provider.NacosProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author 春阳
 * @date 2021-02-19 17:08
 */
@RestController
public class NacosClientController {

    @Autowired
    private NacosProvider nacosProvider;

    @GetMapping("/nacos")
    //@SentinelResource(value = "hello-sentinel", fallback = "fallbackHandler")
    public String nacos() throws InterruptedException {
        /*int time = new Random().nextInt(1000);
        System.out.println("睡眠时间：" + time);
        Thread.sleep(time);*/
        return "Client: " + nacosProvider.nacos();
    }

    public String fallbackHandler() {
        return "已被降级";
    }
}