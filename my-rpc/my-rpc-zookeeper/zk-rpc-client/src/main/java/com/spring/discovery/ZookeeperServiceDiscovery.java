package com.spring.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 春阳
 * @date 2021-02-04 14:24
 */
public class ZookeeperServiceDiscovery implements ServiceDiscovery {

    CuratorFramework curatorFramework;
    LoadBalanceStrategy loadBalanceStrategy = new RoundRobinLocalBalance();

    //服务地址的本地缓存
    List<String> serviceRepos=new ArrayList<>();

    {
        //初始化zookeeper的连接， 会话超时时间是5s，衰减重试
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString("192.168.25.129:2181").sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000, 3)).
                namespace("huakai")
                .build();
        curatorFramework.start();
    }


    @Override
    public String discovery(String serviceName) {
        // 完成了服务地址的查找(服务地址被删除)
        // huakai/com.spring.service.RpcHello
        String path = "/" + serviceName;
        if(serviceRepos.isEmpty()) {
            try {
                serviceRepos = curatorFramework.getChildren().forPath(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //针对已有的地址做负载均衡
        return loadBalanceStrategy.selectHost(serviceRepos);
    }
}
