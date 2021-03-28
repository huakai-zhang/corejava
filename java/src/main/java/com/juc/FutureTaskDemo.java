package com.juc;

import java.util.concurrent.*;

/**
 * @author 花开不合阳春暮
 * @date 2021/3/27 下午12:00
 * 假设有多个线程执行若干任务，每个任务最多只能被执行一次，当多个线程试图同时执行同一个任务时
 * 只允许一个线程执行任务，其他线程需要等待这任务执行完成后才能继续执行
 */
public class FutureTaskDemo {
    private final ConcurrentHashMap<Object, Future<String>> taskCache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        FutureTaskDemo demo = new FutureTaskDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(demo.executionTask("task--1")), String.valueOf(i)).start();
        }
    }

    private String executionTask(final String taskName) {
        while (true) {
            Future<String> future = taskCache.get(taskName);    // 1.1，2.1
            if (future == null) {
                System.out.println("初始化 FutureTask");
                Callable<String> task = () -> {
                    System.out.println("执行 task 任务流程");
                    return taskName;
                };
                // 1.2 创建任务
                FutureTask<String> futureTask = new FutureTask<>(task);
                future = taskCache.putIfAbsent(taskName, futureTask);   // 1.3
                // 反复校验，可能已经被其他线程初始化
                if (future == null) {
                    System.out.println("Future 为空只会出现一次");
                    future = futureTask;
                    futureTask.run();     //1.4
                }
            }
            try {
                return Thread.currentThread().getName() + ": " + future.get();  //1.5,2.2 线程在此等待任务执行完成
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
