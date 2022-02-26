package com.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author 春阳
 * @date 2020-12-23 15:04
 */
public class LockDemo {

    private static String CONNECTION_STR="192.168.25.128:2181,192.168.25.129:2181,192.168.25.130:2181";

    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().
                connectString(CONNECTION_STR).sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,10)).build();
        curatorFramework.start();
        final InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/locks");
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"->尝试获取锁");
                try {
                    lock.acquire();
                    System.out.println(Thread.currentThread().getName()+"->获得锁成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(4000);
                    lock.release();
                    System.out.println(Thread.currentThread().getName()+"->释放锁成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"t" + i).start();
        }
    }
}
