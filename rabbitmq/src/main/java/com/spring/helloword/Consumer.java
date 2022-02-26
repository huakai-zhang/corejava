package com.spring.helloword;

import com.rabbitmq.client.*;
import com.spring.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/28
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();

        // 参数2，是否是持久化消息，要与生产者保持一致
        channel.queueDeclare("hello", true, false, false, null);

        // 消费消息
        // 参数1，队列名称
        // 参数2，开启消息的自动确认机制
        // 参数3，消费消息时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel){

            // 最后一个参数，消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body) = " + new String(body));
            }
        });

        // 如果不关闭，consumer会一直监听队列
        channel.close();
        connection.close();
    }

}
