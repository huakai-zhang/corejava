package com.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        // 异常组
        //System.out.println(blockingQueue.add("a"));
        //System.out.println(blockingQueue.add("b"));
        //System.out.println(blockingQueue.add("c"));
        // 空间不足抛出异常 java.lang.IllegalStateException: Queue full
        //System.out.println(blockingQueue.add("x"));

        //System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());
        // 无可取数据抛出异常 java.util.NoSuchElementException
        //System.out.println(blockingQueue.remove());

        // 队列为空 java.util.NoSuchElementException
        //System.out.println(blockingQueue.element());

        // 特殊值
        //System.out.println(blockingQueue.offer("a"));
        //System.out.println(blockingQueue.offer("b"));
        //System.out.println(blockingQueue.offer("c"));
        // false
        //System.out.println(blockingQueue.offer("x"));

        //System.out.println(blockingQueue.poll());
        //System.out.println(blockingQueue.poll());
        //System.out.println(blockingQueue.poll());
        // null
        //System.out.println(blockingQueue.poll());
        // null
        //System.out.println(blockingQueue.peek());

        // 阻塞
        //blockingQueue.put("a");
        //blockingQueue.put("b");
        //blockingQueue.put("c");
        // 满了之后一直阻塞
        //blockingQueue.put("x");
        //System.out.println(blockingQueue.take());
        //System.out.println(blockingQueue.take());
        //System.out.println(blockingQueue.take());
        // 取完阻塞
        //System.out.println(blockingQueue.take());

        // 超时
        System.out.println(blockingQueue.offer("a", 3L ,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 3L ,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 3L ,TimeUnit.SECONDS));
        // 超时已满 false
        //System.out.println(blockingQueue.offer("x", 3L ,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
        // 超时无可取数据 null
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
    }
}
