package com.zookeeper.zkapi.lock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @author 春阳
 * @date 2020-12-23 14:21
 */
public class LockWatcher implements Watcher {

    private CountDownLatch latch;

    public LockWatcher(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getType()== Event.EventType.NodeDeleted){
            latch.countDown();
        }
    }
}
