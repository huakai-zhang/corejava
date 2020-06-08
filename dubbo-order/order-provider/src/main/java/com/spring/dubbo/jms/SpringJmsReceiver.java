package com.spring.dubbo.jms;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.io.IOException;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/8
 */
public class SpringJmsReceiver {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/service-jms.xml");
        /*JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");

        String msg = (String) jmsTemplate.receiveAndConvert();
        System.out.println(msg);*/
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
