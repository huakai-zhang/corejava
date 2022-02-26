package com.spring.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 春阳
 * @date 2021-01-13 19:11
 */
@RestController
public class ErrorController {

    @GetMapping("/error")
    public String error() {
        return "出错了";
    }
}
