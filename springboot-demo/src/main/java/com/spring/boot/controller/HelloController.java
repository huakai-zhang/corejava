package com.spring.boot.controller;

import com.spring.boot.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(value = "HelloController", tags = "测试API", description = "")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Swagger!";
    }

    @GetMapping("/get_user")
    @ApiOperation("spring 的接口")
    public User getUser(@ApiParam("这个名字会被返回") String username) {
        return new User();
    }

}
