package com.spring.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorClientUtils {

    private static CuratorFramework curatorFramework;

    private final static String CONNECT_STRING = "localhost:2181";

    public static CuratorFramework getInstance() {
        // 创建会话的两种方式
        // 1.normal 方式
        curatorFramework = CuratorFrameworkFactory
                .newClient(CONNECT_STRING, 5000, 5000,
                        new ExponentialBackoffRetry(1000, 3));
        // start方法启动连接
        curatorFramework.start();
        System.out.println("success!");
        return curatorFramework;

        // 2.fluent 风格
        //CuratorFramework curatorFramework1 = CuratorFrameworkFactory.builder().connectString(CONNECT_STRING).sessionTimeoutMs(5000)
        //            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
        //            .namespace("/curator").build();
        //curatorFramework1.start();
    }

}
