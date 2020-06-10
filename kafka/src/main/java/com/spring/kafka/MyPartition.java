package com.spring.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/10
 */
public class MyPartition implements Partitioner {
    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(s);
        int numPart = partitions.size();
        int hashCode = o.hashCode();
        return Math.abs(hashCode%numPart);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
