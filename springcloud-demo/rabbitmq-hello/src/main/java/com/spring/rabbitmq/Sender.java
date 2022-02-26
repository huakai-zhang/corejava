package com.spring.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 春阳
 * @date 2021-01-15 11:00
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sent() {
        String context = "hello " + new Date();
        System.out.println("Sender: " + context);
        this.amqpTemplate.convertAndSend("hello", context);
    }
}
