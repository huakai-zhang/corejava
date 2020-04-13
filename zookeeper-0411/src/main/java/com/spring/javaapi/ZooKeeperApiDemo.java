package com.spring.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.nio.channels.AcceptPendingException;
import java.util.*;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZooKeeperApiDemo implements Watcher {
    private final static String CONNECT_STRING = "localhost:2181";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zooKeeper;
    private static Stat stat;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        getZooKeeper();

        // 创建节点
        Stat stat = zooKeeper.exists("/spring", true);
        if (stat == null) {
            String result = zooKeeper.create("/spring", "spring".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            zooKeeper.getData("/spring", new ZooKeeperApiDemo(), stat);
            System.out.println("创建成功：" + result);
        }

        // 修改数据
        zooKeeper.setData("/spring", "xiaoxiao".getBytes(), -1);
        Thread.sleep(2000);

        // 修改数据
        zooKeeper.setData("/spring", "xiaoxiao123".getBytes(), -1);
        Thread.sleep(2000);

        // 删除数据
        // zooKeeper.delete("/hello", -1);
        // Thread.sleep(2000);

        // 创建节点和子节点
        String path = "/node";
        zooKeeper.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        Thread.sleep(1000);
        stat = zooKeeper.exists(path + "/node1", true);
        if (stat == null) {
            zooKeeper.create(path + "/node1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            Thread.sleep(1000);
        }

        // 修改子节点
        zooKeeper.setData(path + "/node1", "spring123".getBytes(), -1);
        Thread.sleep(1000);

        // 获取指定节点下的子路径
        List<String> children = zooKeeper.getChildren("/node",true);
        System.out.println(children);

        ACL acl1 = new ACL(ZooDefs.Perms.CREATE, new Id("digest", "root:root"));
        ACL acl2 = new ACL(ZooDefs.Perms.CREATE, new Id("ip", "192.168.1.1"));
        List<ACL> acls = new ArrayList<>();
        acls.add(acl1);
        acls.add(acl2);
        zooKeeper.create("/auth", "123".getBytes(), acls, CreateMode.PERSISTENT);
        // 或者
        zooKeeper.addAuthInfo("digest", "root:root".getBytes());
        zooKeeper.create("/auth","123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);

    }

    public static void getZooKeeper() throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(CONNECT_STRING, 5000, new ZooKeeperApiDemo());
        countDownLatch.await();
        System.out.println(zooKeeper.getState());
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        // 如果当前的连接状态就是连接成功的，那么通过计数器去控制
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                countDownLatch.countDown();
                System.out.println(watchedEvent.getState());
            } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                try {
                    // watch监听只会出发一次，要在此触发需要重新注册，true表示重新注册
                    System.out.println("数据变更路径：" + watchedEvent.getPath() + " -> 改变后的值：" +
                            zooKeeper.getData(watchedEvent.getPath(), true, stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 子节点的数据变化触发
            else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    System.out.println("子节点数据变更路径：" + watchedEvent.getPath() + " -> 改变后的值：" +
                            zooKeeper.getData(watchedEvent.getPath(), true, stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 创建节点的时候触发
            else if (watchedEvent.getType() == Event.EventType.NodeCreated) {
                try {
                    System.out.println("节点创建路径：" + watchedEvent.getPath() + " -> 节点的值：" +
                            zooKeeper.getData(watchedEvent.getPath(), true, stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 节点删除会触发
            else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                System.out.println("节点删除路径：" + watchedEvent.getPath());
            }
            System.out.println(watchedEvent.getType());
        }
    }
}
