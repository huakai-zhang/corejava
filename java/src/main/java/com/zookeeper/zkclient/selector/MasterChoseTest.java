package com.zookeeper.zkclient.selector;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 春阳
 * @date 2020-12-23 16:01
 */
public class MasterChoseTest {
    private final static String CONNECT_STRING = "localhost:2181";

    public static void main(String[] args) {
        List<MasterSelector> selectorList = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                ZkClient zkClient = new ZkClient(CONNECT_STRING, 5000, 5000, new SerializableSerializer());
                UserCenter userCenter = new UserCenter();
                userCenter.setMc_id(i);
                userCenter.setMc_name("客户端-" + i);

                MasterSelector selector = new MasterSelector(userCenter, zkClient);
                selectorList.add(selector);
                // 触发选举操作
                selector.start();
                TimeUnit.SECONDS.sleep(4);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            for (MasterSelector masterSelector : selectorList) {
                masterSelector.stop();
            }
        }
    }
}
