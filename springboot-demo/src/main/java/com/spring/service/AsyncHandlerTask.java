package com.spring.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class AsyncHandlerTask {

    private AtomicInteger num = new AtomicInteger();

    @Async("taskExecutor")
    public void sendMessage(){
        System.out.println(Thread.currentThread().getName() + " start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num.incrementAndGet();
        System.out.println(Thread.currentThread().getName() + "-------执行完成，总数：" + num);
    }

}
