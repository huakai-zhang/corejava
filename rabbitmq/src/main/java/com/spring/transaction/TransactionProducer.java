package com.spring.transaction;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.spring.utils.RabbitMQUtils;

import java.io.IOException;

public class TransactionProducer {
    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();
        // 将通道声明指定的交换机
        // 参数1，交换机名称
        // 参数2，交换机类型，fanout为广播类型
        channel.exchangeDeclare("logs", "fanout");

        try {
            // 事务模式           confirm模式参考com.spring.topic.Provider
            channel.txSelect();
            // 发送消息
            channel.basicPublish("logs", "", null, "fanout type message".getBytes());
            //int i = 1 / 0;
            channel.txCommit();
            System.out.println("消息发送成功");
        } catch (Exception e) {
            channel.txRollback();
            System.out.println("消息已经会滚");
        }

        RabbitMQUtils.closeConnectAndChanel(channel, connection);

    }
}
