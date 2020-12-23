package com.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 春阳
 * @date 2020-12-23 11:53
 */
public class SessionDemo {
    private final static String CONNECT_STRING = "192.168.25.130:2181";

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient(CONNECT_STRING, 4000);
        System.out.println(zkClient + " - > success!");

        //zkClient.createEphemeral("/zkclient");
        // zkclient 提供递归创建父节点的功能
        //zkClient.createPersistent("/node/node1/node1-1", true);
        //System.out.println("create success!");

        // 删除节点
        //zkClient.deleteRecursive("/node");

        // 获取子节点
        List<String> list = zkClient.getChildren("/node");
        System.out.println(list);

        // watcher
        zkClient.subscribeDataChanges("/node", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("节点名称：" + s + "-> 节点修改后的值：" + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        });

        zkClient.writeData("/node", "node");
        TimeUnit.SECONDS.sleep(2);

        zkClient.subscribeChildChanges("/node", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {

            }
        });
    }
}
