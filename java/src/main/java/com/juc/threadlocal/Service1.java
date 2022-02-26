package com.juc.threadlocal;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Service1 {
    private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
    public void service1(User user){
        //给ThreadLocal赋值，后续的服务直接通过ThreadLocal获取就行了。
        UserContextHolder.holder.set(user);
        new Service2().service2();
    }

    public static void main(String[] args) throws InterruptedException {
        User user = new User("Spring");
        new Service1().service1(user);

        /*for (int i = 0; i < 100; ++i) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    User user = new User("Spring");
                    new Service1().service1(user);
                }
            });
            Thread.sleep(100);
        }
        poolExecutor.shutdown();*/
    }
}
