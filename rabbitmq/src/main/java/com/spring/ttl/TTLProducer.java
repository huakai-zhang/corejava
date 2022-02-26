package com.spring.ttl;

import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.spring.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 春阳
 * @date 2021-05-19 17:32
 */
public class TTLProducer {
    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();

        // 绑定死信交换机
        Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("x-dead-letter-exchange", "DLX_EXCHANGE");
        // 队列中的消息未被消费 11 秒后过期
        arguments.put("x-message-ttl", 11000);
        channel.exchangeDeclare("DLX_TEST_EXCHANGE", "topic");
        channel.queueDeclare("DLX_TEST", true, false, false, arguments);
        channel.queueBind("DLX_TEST", "DLX_TEST_EXCHANGE", "spring.test");

        BasicProperties properties = new BasicProperties().builder().expiration("2000").build();
        // 发送消息
        channel.basicPublish("DLX_TEST_EXCHANGE", "spring.test", properties, ("发送死信消息").getBytes());

        RabbitMQUtils.closeConnectAndChanel(channel, connection);
    }
}
