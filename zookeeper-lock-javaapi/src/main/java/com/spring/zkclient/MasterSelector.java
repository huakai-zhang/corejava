package com.spring.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/13
 */
public class MasterSelector {
    private ZkClient zkClient;

    // 需要争抢的节点
    private final static String MASTER_PATH = "/master";

    // 注册节点内容变化
    private IZkDataListener dataListener;

    // 其他服务器
    private UserCenter server;

    // master服务器
    private UserCenter master;

    private static boolean isRunning = false;

    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public MasterSelector(UserCenter server) {
        this.server = server;

        this.dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                // 节点如果被删除，发起选主操作
                chooseMaster();
            }
        };
    }

    public void start() {
        // 开始选举
        if (!isRunning) {
            isRunning = true;
            zkClient.subscribeDataChanges(MASTER_PATH, dataListener);
        }
    }

    public void stop() {
        // 停止选举
    }

    // 具体选master的实现逻辑
    private void chooseMaster() {
        if (!isRunning) {
            System.out.println("当前服务没有启动");
            return;
        }
        try {
            zkClient.createEphemeral(MASTER_PATH, server);
            // 把server节点赋值给master
            master = server;
            System.out.println(master.getMc_name() + "->我现在已经是master，你们要听我的");

            // 定时器
            // master释放（master 出现故障）
            scheduledExecutorService.schedule(() -> {
                releaseMaster();
            }, 5, TimeUnit.SECONDS);
        } catch (ZkNodeExistsException e) {
            // master已经存在
            UserCenter userCenter = zkClient.readData(MASTER_PATH, true);
            if (userCenter == null) {
                chooseMaster();
            } else {
                master = userCenter;
            }

        }

    }

    private void releaseMaster() {
        // 释放锁（故障模拟）
        // 判断当前是不是master，只有master才需要释放
        if (checkIsMaster()) {
            zkClient.deleteRecursive(MASTER_PATH);
        }
    }

    private boolean checkIsMaster() {
        // 判断当前是不是master
        UserCenter userCenter = zkClient.readData(MASTER_PATH);
        if (userCenter.getMc_name().equals(server.getMc_name())) {
            master = userCenter;
            return true;
        }
        return false;
    }
}
