package com.spring.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

@Slf4j
public class RedisConsumer {

    @RabbitListener(queues = {"DZ_DEAD_LETTER_QUEUE"}, containerFactory = "rabbitListenerContainerFactory")
    public void receive(String msg, Channel channel, Message message) {
        log.info(msg);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
