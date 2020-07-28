package com.spring.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.spring.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/28
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();
        // 将通道声明指定的交换机
        // 参数1，交换机名称
        // 参数2，交换机类型，topic 路由模式
        channel.exchangeDeclare("topics", "topic");

        String routingKey = "user.save";
        // 发送消息
        channel.basicPublish("topics", routingKey, null, ("topic type message, key: " + routingKey).getBytes());

        RabbitMQUtils.closeConnectAndChanel(channel, connection);

    }
}
