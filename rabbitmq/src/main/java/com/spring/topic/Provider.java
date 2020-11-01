package com.spring.topic;

import com.rabbitmq.client.*;
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

        // 指定我们的消息投递模式: 消息的确认模式
        channel.confirmSelect();

        // 添加一个异步确认监听
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("-------ack!-----------");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("-------no ack!-----------");
            }
        });

        // 监听器收到了无法路由的消息
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText,
                                     String exchange, String routingKey,
                                     AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msg = new String(body);
                System.out.println("replyText:"+replyText);
                System.out.println("exchange:"+exchange);
                System.out.println("routingKey:"+routingKey);
                System.out.println("msg:"+msg);
            }
        });

        // 将通道声明指定的交换机
        // 参数1，交换机名称
        // 参数2，交换机类型，topic 路由模式
        channel.exchangeDeclare("topics", "topic");

        String routingKey = "user.save";
        // 发送消息
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("topics", routingKey, null, ("topic type message, key: " + routingKey).getBytes());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 无法路由的情况
        //String routingKey = "user1212.save";
        // 第三个参数设置mandatory，如果mandatory为false，消息会被直接丢弃
        //channel.basicPublish("topics", routingKey, true, null, ("topic type message, key: " + routingKey).getBytes());


        /*try {
            // 普通confirm，发送一条确认一条
            *//*if (channel.waitForConfirms()) {
                System.out.println("消息发送完毕");
            }*//*
            // 批量确认
            channel.waitForConfirmsOrDie();
            System.out.println("消息全部发送完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        RabbitMQUtils.closeConnectAndChanel(channel, connection);
    }
}
