package com.spring.controller;

import com.spring.entity.User;
import com.spring.service.UserService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 春阳
 * @date 2021-03-23 17:30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/list")
    public String hello() {
        RLock lock = redissonClient.getLock("stock");
        lock.lock();
        try {
            List<User> userList = userService.getUserByAccount("ed2df4c3-2559-11eb-9365-0c9d9211a1c7");
            return userList.toString();
        } finally {
            lock.unlock();
        }
    }

}
