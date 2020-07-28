package com.spring.helloword;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.spring.utils.RabbitMQUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/28
 */
public class Producer {

    // 生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        // 获取连接中通道
        Channel channel = connection.createChannel();

        // 通道绑定对应的消息队列
        // 参数1，队列名称，如果不存在自动创建
        // 参数2，队列特性是否持久化
        // 参数3，是否独占队列
        // 参数4，是否在消费完成后自动删除队列
        // 参数5，额外附加参数
        channel.queueDeclare("hello", false, false, false, null);

        // 发布消息
        // 参数1，交换机名称
        // 参数2，队列名称
        // 参数3，传递消息额外设置，MessageProperties.PERSISTENT_TEXT_PLAIN，RabbitMQ重启后消息依然有效
        // 参数4，消息的具体内容
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "Hello RabbitMQ".getBytes());

        //channel.close();
        //connection.close();
        RabbitMQUtils.closeConnectAndChanel(channel, connection);
    }

}
