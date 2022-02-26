package com.zookeeper.zkapi;

import org.apache.zookeeper.*;

/**
 * @author 花开不合阳春暮
 * @date 2020/12/27 下午8:08
 */
public class WatcherDemo {
    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 4000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("event.type:" + watchedEvent.getType());
            }
        });
        // 创建节点
        zooKeeper.create("/watch", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // 注册监听
        zooKeeper.exists("/watch", true);
        Thread.sleep(1000);
        // 修改节点的值触发监听
        zooKeeper.setData("/watch", "1".getBytes(), -1);
        System.in.read();
    }
}
