package com.juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo01 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                //String data = new ThreadLocalDemo01().date(finalI);
                String data = new ThreadLocalDemo01().threadLocalDate(finalI);
                System.out.println(data);
            });
        }
        threadPool.shutdown();
    }

    private String date(int seconds){
        Date date = new Date(1000 * seconds);
        // 每个任务都创建了一个 simpleDateFormat 对象，也就是说，1000 个任务对应 1000 个 simpleDateFormat 对象
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(date);
    }

    private String threadLocalDate(int seconds){
        Date date = new Date(1000 * seconds);
        // 16个线程对应16个 SimpleDateFormat，每个线程有自己的副本，是线程安全的
        SimpleDateFormat dateFormat = ThreadSafeFormater.dateFormatThreadLocal.get();
        return dateFormat.format(date);
    }
}
