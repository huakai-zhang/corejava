package com.spring.dubbo.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/8
 */
public class JmsReceiver {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.174.128:61616");
        Connection connection = null;
        try {
            // 创建连接
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            // 创建队列（如果队列已经存在则不会创建，first-queue是队列名称）
            // destination 表示目的地
            Destination destination = session.createQueue("first-queue");
            // 创建消息消费者
            MessageConsumer consumer = session.createConsumer(destination);
            // 配置非阻塞模式
            /*consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {

                }
            });*/
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println(message.getText());
            System.out.println("key的属性：" + message.getStringProperty("key"));
            session.commit();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
