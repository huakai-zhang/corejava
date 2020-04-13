package com.spring.javaapilock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/13
 */
public class LockWatcher implements Watcher{

    private CountDownLatch latch;

    public LockWatcher(CountDownLatch latch) {
        this.latch = latch;
    }

    public void process(WatchedEvent event) {
        if(event.getType()== Event.EventType.NodeDeleted){
            latch.countDown();
        }
    }
}
