package com.spring.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.spring.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/28
 */
public class Producer {

    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        // 获取通道对象
        Channel channel = connection.createChannel();

        // 通过通道声明队列
        channel.queueDeclare("work", true, false, false, null);

        // 生产消息
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "work", null, ("Hello Work Queue" + i).getBytes());
        }

        RabbitMQUtils.closeConnectAndChanel(channel, connection);
    }

}
