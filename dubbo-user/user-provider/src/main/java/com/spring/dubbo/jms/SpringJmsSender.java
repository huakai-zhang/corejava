package com.spring.dubbo.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/8
 */
public class SpringJmsSender {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("E:\\JAVA\\IdeaProjects\\corejava\\dubbo-user\\user-provider\\src\\main\\resources\\META-INF\\spring\\service-jms.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");

        jmsTemplate.send(session -> session.createTextMessage("Hello,晓晓"));
    }
}
