package com.juc;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author 春阳
 * @date 2021-03-30 17:52
 */
public class ComposedFutureDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1 执行 start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1 执行 end");
            return UUID.randomUUID().toString();
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 执行 start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future2 执行 end");
            return UUID.randomUUID().toString();
        });
        /*CompletableFuture<String> future3 = future1.thenCombine(future2, (s1, s2) -> {
            System.out.println("future3 执行");
            return s1 + "-" + s2;
        });*/
        /*CompletableFuture<Void> future3 = future1.thenAcceptBoth(future2, (s1, s2) -> {
            System.out.println("future3 执行: " + (s1 + "-" + s2));
        });*/
        /*CompletableFuture<Void> future3 = future1.runAfterBoth(future2, () -> {
            System.out.println("在 future1 和 future2 执行完后执行的线程");
        });*/
        /*CompletableFuture<String> future3 = future1.applyToEither(future2, t -> {
            System.out.println("future3 执行");
            return t;
        });*/
        /*CompletableFuture<Void> future3 = future1.acceptEither(future2, t ->
            System.out.println("future3 执行: " + t)
        );*/
        /*CompletableFuture<Void> future3 = future1.runAfterEither(future2, () ->
                System.out.println("future3 执行")
        );*/

        //System.out.println(future3.get());
        //CompletableFuture.allOf(future1, future2).join();
        CompletableFuture.anyOf(future1, future2).join();
        System.out.println(future1.get());
        System.out.println(future2.get());

        System.out.println("完毕");
    }
}
