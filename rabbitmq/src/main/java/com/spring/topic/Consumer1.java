package com.spring.topic;

import com.rabbitmq.client.*;
import com.spring.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/28
 */
public class Consumer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();

        // 通道绑定交换机
        channel.exchangeDeclare("topics", "topic");

        // 临时队列
        String queueName = channel.queueDeclare().getQueue();

        // 绑定队列和交换机，动态通配符形式route key
        channel.queueBind(queueName, "topics",  "user.*");

        // 消费消息
        channel.basicConsume(queueName, false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body) + "====" + envelope.getDeliveryTag());
                channel.basicAck(envelope.getDeliveryTag(), false);
                // 拒绝消息
                // requeue 是否重新入队列，true 是，false 直接丢弃，相当于告诉队列可以直接删除掉
                channel.basicReject(envelope.getDeliveryTag(), false);
                // 批量拒绝
                // multiple true可拒绝包含DeliveryTag的所有消息；如果为false，则仅拒绝当前DeliveryTag
                channel.basicNack(envelope.getDeliveryTag(), true, false);
            }
        });
    }

}
