package com.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/13 下午10:39
 */
@RestController
@RefreshScope // 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。
public class TestController {

    @Value("${from}")
    private String from;

    @GetMapping("/from")
    public String from() {
        return this.from;
    }

}
