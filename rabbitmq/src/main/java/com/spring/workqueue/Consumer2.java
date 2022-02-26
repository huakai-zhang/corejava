package com.spring.workqueue;

import com.rabbitmq.client.*;
import com.spring.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/28
 */
public class Consumer2 {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        final Channel channel = connection.createChannel();

        // 每次只能消费一个消息
        channel.basicQos(1);
        // 参数2，是否是持久化队列，要与生产者保持一致
        channel.queueDeclare("work", true, false, false, null);

        // 消费消息
        // 参数1，队列名称
        // 参数2，开启消息的自动确认机制
        // 参数3，消费消息时的回调接口
        channel.basicConsume("work", false, new DefaultConsumer(channel){

            // 最后一个参数，消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2：" + new String(body));
                // 参数1：确认队列中那个具体消息
                // 参数2：是否开启多消息同时确认
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }

}
