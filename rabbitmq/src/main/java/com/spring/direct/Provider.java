package com.spring.direct;

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
        // 参数2，交换机类型，direct 路由模式
        channel.exchangeDeclare("logs_direct", "direct");

        String routingKey = "info";
        // 发送消息
        channel.basicPublish("logs_direct", routingKey, null, ("direct type message, key: " + routingKey).getBytes());

        RabbitMQUtils.closeConnectAndChanel(channel, connection);

    }
}
