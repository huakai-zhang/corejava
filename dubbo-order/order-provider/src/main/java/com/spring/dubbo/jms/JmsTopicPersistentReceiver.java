package com.spring.dubbo.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/8
 */
public class JmsTopicPersistentReceiver {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.174.128:61616");
        Connection connection = null;
        try {
            // 创建连接
            connection = connectionFactory.createConnection();
            // 设置持久订阅
            connection.setClientID("DUBBO-ORDER");
            connection.start();

            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic("first-topic");
            MessageConsumer consumer = session.createDurableSubscriber(topic, "DUBBO-ORDER");

            TextMessage message = (TextMessage) consumer.receive();
            System.out.println(message.getText());
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
