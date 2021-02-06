package com.spring.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
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
                connectString("localhost:2181").sessionTimeoutMs(5000).
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
                registryWatch(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //针对已有的地址做负载均衡
        return loadBalanceStrategy.selectHost(serviceRepos);
    }

    public void registryWatch(final String path) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, true);
        PathChildrenCacheListener listener = (curatorFramework1, pathChildrenCacheEvent) -> {
            serviceRepos = curatorFramework1.getChildren().forPath(path);
            System.out.println("==========获取到注册的服务变更：" + serviceRepos);
        };
        pathChildrenCache.getListenable().addListener(listener);
        pathChildrenCache.start(PathChildrenCache.StartMode.NORMAL);
    }
}
