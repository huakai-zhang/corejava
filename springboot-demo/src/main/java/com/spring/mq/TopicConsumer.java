package com.spring.mq;

//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/28
 */
//@Component
public class TopicConsumer {

    /*@RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topics", type = "topic"),
                    key = {"user.*"}
            )
    })
    public void receive(String msg, Channel channel, Message message) throws IOException {
        try {
            System.out.println("message = " + msg);
            //Thread.sleep(2000);
            //int i = 1 / 0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topics", type = "topic"),
                    key = {"order.#", "product.#"*//*, "user.*"*//*}
            )
    })
    public void receive2(String message) {
        System.out.println("message2 = " + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive3(String msg, Channel channel, Message message) {
        System.out.println("work-message1 = " + message);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive23(String message) {
        System.out.println("work-message2 = " + message);
    }*/
}
