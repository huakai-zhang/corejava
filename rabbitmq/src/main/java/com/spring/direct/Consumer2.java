package com.spring.direct;

import com.rabbitmq.client.*;
import com.spring.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/28
 */
public class Consumer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 通道绑定交换机
        channel.exchangeDeclare("logs_direct", "direct");

        // 临时队列
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "logs_direct",  "error");
        channel.queueBind(queueName, "logs_direct",  "info");
        channel.queueBind(queueName, "logs_direct",  "waring");

        // 消费消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2：" + new String(body));
            }
        });
    }

}
