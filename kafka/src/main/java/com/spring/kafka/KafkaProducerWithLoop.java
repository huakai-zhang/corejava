package com.spring.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/10
 */
public class KafkaProducerWithLoop implements Runnable {

    private final org.apache.kafka.clients.producer.KafkaProducer<Integer, String> producer;


    public KafkaProducerWithLoop() {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaProperties.KAFKA_BROKER_LIST);
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("client.id", "producerDemo");

        this.producer = new org.apache.kafka.clients.producer.KafkaProducer<Integer, String>(props);
    }

    public void senMsg() {
        producer.send(new ProducerRecord<>(KafkaProperties.TOPIC, 1, "message"), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                System.out.println("message send to:[" + recordMetadata.partition() + "], offset:[" + recordMetadata.offset() + "]");
            }
        });
    }

    public static void main(String[] args) {
        KafkaProducerWithLoop producer = new KafkaProducerWithLoop();
        new Thread(producer).start();
    }

    @Override
    public void run() {
        int messageNo = 0;
        while (true) {
            String messageStr = "message-" + messageNo;
            producer.send(new ProducerRecord<>(KafkaProperties.TOPIC, messageNo, messageStr), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println("message send to:[" + recordMetadata.partition() + "], offset:[" + recordMetadata.offset() + "]");
                }
            });
            ++messageNo;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
