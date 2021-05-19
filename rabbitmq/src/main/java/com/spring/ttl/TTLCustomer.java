package com.spring.ttl;

import com.rabbitmq.client.*;
import com.spring.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author 春阳
 * @date 2021-05-19 17:37
 */
public class TTLCustomer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();

        // 通道绑定交换机
        channel.exchangeDeclare("DLX_EXCHANGE", "topic");

        // 临时队列
        String queueName = channel.queueDeclare().getQueue();

        // 无条件路由
        channel.queueBind(queueName, "DLX_EXCHANGE",  "#");

        // 消费消息
        channel.basicConsume(queueName, false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("死信队列消费者：" + new String(body) + "====" + envelope.getDeliveryTag());
            }
        });
    }
}
