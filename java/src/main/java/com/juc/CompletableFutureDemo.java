package com.juc;

import com.mybatis.spring.executor.Executor;

import java.util.concurrent.*;
import java.util.function.Function;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // runAsync 方法不支持返回值
        CompletableFuture<Void> c1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回");
        });
        System.out.println("c1: " + c1.get());

        // 异步回调
        // supplyAsync 可以支持返回值
        CompletableFuture<Integer> c2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有返回");
            // 如果不声明 exceptionally，handle 或 whenComplete 会打印 u
            //int age = 10 / 0;
            return 1024;
        }, executorService);

        // 返回 CompletableFuture，处理结果在新返回的CompletableFuture中获取
        /*
        CompletableFuture<Integer> c3 = c2.thenApply(t -> {
            System.out.println("***********t: " + t);
            return t * 10;
        });*/

        // 调用函数，并执行 Future
        /*CompletableFuture<Integer> c3 = c2.thenCompose(isDigits -> {
            System.out.println("***********调用函数");
            return CompletableFuture.supplyAsync(
                    () -> {
                        System.out.println("***********执行 Future");
                        return isDigits * 100;
                    }
            );
        });*/

        // 处理结果或错误
        /*CompletableFuture<Integer> c3 = c2.handle((t, u) -> {
            System.out.println("***********t: " + t);
            System.out.println("***********u: " + u);
            return t * 1000;
        });*/

        /*c2.whenComplete((t, u) -> {
            System.out.println("***********t: " + t);
            System.out.println("***********u: " + u);
        });

        c2.exceptionally(f -> {
            System.out.println("***********exception: " + f.getMessage());
            return 404;
        });*/

        System.out.println(c2.get());
        //System.out.println(c3.get());
        System.out.println("线程池完成任务个数：" + ((ThreadPoolExecutor)executorService).getCompletedTaskCount());
        Thread.sleep(2000);
        executorService.shutdown();
    }
}
