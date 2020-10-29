package com.spring.boot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class AsyncHandlerTask {

    private AtomicInteger num = new AtomicInteger();

    @Async("taskExecutor")
    public void sendMessage(){
        num.incrementAndGet();
        System.out.println(Thread.currentThread().getName() + "-------执行完成，总数：" + num);
    }

}
