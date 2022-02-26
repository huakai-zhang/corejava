package com.juc.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 春阳
 * @date 2021-03-30 11:47
 */
public class ResultSetMergeDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 1
        CompletableFuture<Integer> age = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("查询年龄：耗费 1 秒");
            return 18;
        }, executorService);

        // 2
        CompletableFuture<String> name = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("查询姓名：耗费 3 秒");
            return "xanyi";
        }, executorService);

        // 3
        CompletableFuture<Double> price = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("查询薪水：耗费 5 秒");
            return 5001.23;
        }, executorService);

        CompletableFuture.allOf(age, name, price).join();
        //CompletableFuture.anyOf(age, name, price).join();
        System.out.println("薪水：" + price.get());
        System.out.println("年龄：" + age.get());
        System.out.println("姓名：" + name.get());

        executorService.shutdown();
    }
}
