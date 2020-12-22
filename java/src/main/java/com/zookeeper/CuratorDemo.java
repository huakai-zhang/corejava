package com.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author 春阳
 * @date 2020-12-22 16:33
 */
public class CuratorDemo {

    private static String CONNECTION_STR="192.168.25.128:2181,192.168.25.129:2181,192.168.25.130:2181";

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework=CuratorFrameworkFactory.builder().
                connectString(CONNECTION_STR).sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).build();

        curatorFramework.start(); //启动
        createData(curatorFramework);
    }

    private static void createData(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                forPath("/data/program","test".getBytes());
    }
}
