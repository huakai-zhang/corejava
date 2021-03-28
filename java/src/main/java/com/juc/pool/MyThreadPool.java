package com.juc.pool;

import java.util.concurrent.*;

public class MyThreadPool {
    // maximumPoolSize
    // CPU 密集型 Runtime.getRuntime().availableProcessors()(CPU核心数，逻辑处理器) + 1~2
    // IO 密集型 CPU核心数/(1-阻塞系数)，实际应用2 * cpu
    private static ExecutorService threadPool = new ThreadPoolExecutor(
            20,
            500,
            2L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1500),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
        try {
            for (int i = 1; i <= 2000; i++) {
                int finalI = i;
                threadPool.execute(() -> {
                    try {
                        if (finalI < 50) {
                            Thread.sleep(finalI * 200);
                        } else {
                            Thread.sleep(finalI * 50);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //threadPool.shutdownNow();
        }

        // 线程池的监控
        ThreadPoolExecutor tpe = ((ThreadPoolExecutor) threadPool);
        while (true) {
            long taskCount = tpe.getTaskCount();
            System.out.println("线程池所需要执行的任务数量："+ taskCount);

            long completedTaskCount = tpe.getCompletedTaskCount();
            System.out.println("执行完成的任务数量："+ completedTaskCount);

            int poolSize =  tpe.getPoolSize();
            System.out.println("线程池的线程数量："+ poolSize);

            int queueSize = tpe.getQueue().size();
            System.out.println("当前排队线程数："+ queueSize);

            int activeCount = tpe.getActiveCount();
            System.out.println("当前活动线程数："+ activeCount);

            int largestPoolSize = tpe.getLargestPoolSize();
            System.out.println("曾经创建过的最大线程数量："+ largestPoolSize);

            System.out.println("/***********************************************/");
            Thread.sleep(3000);
        }
    }
}
