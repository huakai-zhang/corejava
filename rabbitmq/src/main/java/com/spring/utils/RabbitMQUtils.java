package com.spring.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/28
 */
public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;

    static {
        // 创建连接mq的连接工厂对象
        connectionFactory = new ConnectionFactory();
        // 设置连接rabbitmq主机
        connectionFactory.setHost("localhost");
        // 设置端口号
        connectionFactory.setPort(5672);
        // 设置连接那个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        // 设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
    }

    // 定义提供连接对象的方法
    public static Connection getConnection() {
        try {
            // 获取连接对象
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 关闭通道和关闭连接工具方法
    public static  void closeConnectAndChanel(Channel channel, Connection connection) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
