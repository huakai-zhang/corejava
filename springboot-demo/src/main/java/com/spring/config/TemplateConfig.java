package com.spring.config;

import com.spring.entity.MyBean;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TemplateConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                System.out.println("发送消息成功");
            }
            if (!ack) {
                System.out.println("发送消息失败：" + cause);
                throw new RuntimeException("发送异常：" + cause);
            }
        });

        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("replyCode:"+replyCode);
            System.out.println("replyText:"+replyText);
            System.out.println("exchange:"+exchange);
            System.out.println("routingKey:"+routingKey);
        });

        return rabbitTemplate;
    }

    @Bean
    @Scope("session")
    public MyBean myBean() {
        return new MyBean();
    }
}