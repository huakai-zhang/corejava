package com.spring.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CuratorCreateSessionDemo {

    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorClientUtils.getInstance();

        try {
            // 创建节点
            //String result = curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                    //.forPath("/curator/curator1/curator1-1", "123".getBytes());

            // 删除节点，默认情况下，version为-1
            //curatorFramework.delete().deletingChildrenIfNeeded().forPath("/node");

            // 更新
//            Stat stat1 = curatorFramework.setData().forPath("/curator", "123".getBytes());
//            System.out.println(stat1);
//
//            // 查询
//            Stat stat = new Stat();
//            byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/curator");
//            System.out.println(new String(bytes) + "-->stat:" + stat);

            // 异步操作
            ExecutorService service = Executors.newFixedThreadPool(1);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                    .inBackground(new BackgroundCallback() {
                        @Override
                        public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                            System.out.println(Thread.currentThread().getName() + "->resultCode:" + curatorEvent.getResultCode()
                                    + "->" + curatorEvent.getType());
                            countDownLatch.countDown();
                        }
                    },service).forPath("/spring", "123".getBytes());
            countDownLatch.await();
            service.shutdownNow();

            // 事务操作（curator独有）
            Collection<CuratorTransactionResult> results = curatorFramework.inTransaction().create().forPath("/trans", "111".getBytes())
                    .and().setData().forPath("/xxxx", "111".getBytes()).and().commit();
            for (CuratorTransactionResult result : results) {
                System.out.println(result.getForPath() + "->" + result.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
