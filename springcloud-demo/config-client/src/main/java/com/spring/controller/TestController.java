package com.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/13 下午10:39
 */
@RestController
public class TestController {

    @Value("${from}")
    private String from;

    @GetMapping("/from")
    public String from() {
        return this.from;
    }

}
