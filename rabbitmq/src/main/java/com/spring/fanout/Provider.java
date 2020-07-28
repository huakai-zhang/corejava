package com.spring.fanout;

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
        // 参数2，交换机类型，fanout为广播类型
        channel.exchangeDeclare("logs", "fanout");

        // 发送消息
        channel.basicPublish("logs", "", null, "fanout type message".getBytes());

        RabbitMQUtils.closeConnectAndChanel(channel, connection);

    }
}
