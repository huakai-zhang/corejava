package com.spring.kafka;

import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/10
 */
public class CommitDemoConsumer extends ShutdownableThread {

    private final KafkaConsumer<Integer, String> consumer;

    private List<ConsumerRecord> buffer = new ArrayList<>();

    public CommitDemoConsumer() {
        super("KafkaConsumerTest", false);
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_BROKER_LIST);
        // 消息所属的分组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoGroup1");
        // 是否自动提交消息offset
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        // 自动提交的间隔时间
        //properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        // 设置最开始的offset偏移量为当前group.id的最早消息
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // 设置心跳时间
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        // 对key和value设置反序列化对象
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        this.consumer = new KafkaConsumer<>(properties);
        // 指定消费的分区
        TopicPartition p0 = new TopicPartition(KafkaProperties.TOPIC, 0);
        this.consumer.assign(Arrays.asList(p0));
    }


    @Override
    public void doWork() {
        // 指定分区后无需订阅（指定消费与订阅互斥）
        //consumer.subscribe(Collections.singletonList(KafkaProperties.TOPIC));
        ConsumerRecords<Integer, String> records = consumer.poll(1000);
        for (ConsumerRecord record : records) {
            System.out.println("receiver message:[" + record.key() + "->" + record.value() + "],offset:" + record.offset());
            buffer.add(record);
        }
        if (buffer.size() >= 5) {
            System.out.println("Begin Execute Commit Offset Operation");
            consumer.commitAsync();
            buffer.clear();
        }
    }

    public static void main(String[] args) {
        CommitDemoConsumer consumer = new CommitDemoConsumer();
        consumer.start();
    }
}
