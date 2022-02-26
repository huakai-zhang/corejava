package com.spring.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRabbitMQ {

    //@Autowired
    //private RabbitTemplate rabbitTemplate;

    @Test
    public void testFanout() {
        //rabbitTemplate.convertAndSend("logs", "", "Fanout模型");
        //rabbitTemplate.convertAndSend("topics", "user11.save", "topic无法路由");
        //rabbitTemplate.convertAndSend("topics", "user.save", "topic路由");
        //rabbitTemplate.convertAndSend("topics", "user.save", "topic投递的第二条消息，必须等到上一条消息ACK之后才会被投递");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
