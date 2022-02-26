package com.spring.controller;

import com.spring.entity.User;
import com.spring.entity.dto.UserSearchReq;
import com.spring.service.MemberService;
import com.spring.service.UserDS1Service;
import com.spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author 春阳
 * @since 2021-03-23 17:30
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDS1Service userDS1Service;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private MemberService memberService;
    //@Autowired
    //private RedissonClient redissonClient;

    @PostMapping("/list")
    public List<User> list(@Valid @RequestBody UserSearchReq req) {
        /*RLock lock = redissonClient.getLock("stock");
        lock.lock();
        try {
            List<User> userList = userService.getUserByAccount("ed2df4c3-2559-11eb-9365-0c9d9211a1c7");
            return userList.toString();
        } finally {
            lock.unlock();
        }*/
        return userService.getUserByCondition(req.getId(), req.getRandomUser());
    }

    @PostMapping("/list1")
    public List<User> list1(@Valid @RequestBody UserSearchReq req) {
        return userDS1Service.getUserByCondition(req.getId(), req.getRandomUser());
    }

    /**
     * 多线程执行任务
     */
    @GetMapping("/testThread")
    public Double testThread() throws ExecutionException, InterruptedException {
        Future<Double> future = threadPoolTaskExecutor.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("我执行完了");
            return Math.random();
        });
        Future<Double> future1 = threadPoolTaskExecutor.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("我执行完了");
            return Math.random();
        });
        Future<Double> future2 = threadPoolTaskExecutor.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("我执行完了");
            return Math.random();
        });
        return future.get() + future1.get() + future2.get();
    }

    /**
     * 异步执行
     */
    @GetMapping("/asyncThread")
    public void asyncThread() {
        userService.asyncThread();
    }
}
