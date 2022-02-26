package com.ali.nacos;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author 花开不合阳春暮
 * @date 2021/2/27 下午12:34
 */
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行");
                executorService.execute(this);
            }
        });
    }
}
