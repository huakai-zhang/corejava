package com.juc.connectionpool;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 花开不合阳春暮
 * @date 2021/4/18 下午12:33
 */
public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);
    // 保证所有 ConnectionRunner 同时开始
    static CountDownLatch start = new CountDownLatch(1);
    // main 线程会等待所有 ConnectionRunner 结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        // 线程数量，可修改数量进行观察
        int threadCount = 30;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection: " + notGot);
        System.out.println("not got rote: " + new BigDecimal(notGot.intValue()).divide(new BigDecimal(threadCount * count),
                4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)) + "%");
    }

    static class ConnectionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
            }
            while (count > 0) {
                try {
                    // 从线程池中获取链接，如果 1000 ms 内无法获得到，返回null
                    // 分别统计链接获取数量 got 和未获取到数量 notGot
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception e) {
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }

}
