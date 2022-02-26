package com.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author 春阳
 * @date 2020-12-23 15:11
 */
public class SelectorClient extends LeaderSelectorListenerAdapter implements Closeable {
    private final String name;
    private final LeaderSelector leaderSelector;
    public SelectorClient (CuratorFramework client, String path, String name) {
        this.name = name;
        // 利用一个给定的路径创建一个 leaderselector
        // 执行 leader 选举的所有参与者对应的路径必须一样
        // 本例中 SelectorClient 也是一个LeaderSelectorListener，但这不是必须的。
        leaderSelector = new LeaderSelector(client, path, this);
        // 在大多数情况下，我们会希望一个 selector放弃 leader 后还要重新参与 leader 选举
        leaderSelector.autoRequeue();
    }
    public void start(){
        leaderSelector.start();
    }
    @Override
    public void close() throws IOException {
        leaderSelector.close();
    }
    @Override
    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
        //选举为 master
        System.out.println(name + " 现在是 leader了，持续成为 leader ");

        //阻塞，让当前获得 leader权限的节点一直持有，直到该进程关闭
        System.in.read();
    }
    private static String CONNECTION_STR="192.168.25.128:2181,192.168.25.129:2181,192.168.25.130:2181";
    public static void main(String[] args) throws IOException {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().
                connectString(CONNECTION_STR).sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        curatorFramework.start();
        SelectorClient sc=new SelectorClient(curatorFramework,"/leader","ClientA");
        sc.start();
        System.in.read();
    }
}
