package com.spring.dubbo.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/8
 */
public class JmsUnCommitSender {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.174.128:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
            Destination destination = session.createQueue("first-queue");

            MessageProducer producer = session.createProducer(destination);
            for (int i = 0; i < 5; i++) {
                TextMessage message = session.createTextMessage("hello，晓晓！我是Spring"+ i);
                producer.send(message);
            }

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
