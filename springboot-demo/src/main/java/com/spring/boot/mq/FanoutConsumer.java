package com.spring.boot.mq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/28
 */
@Component
public class FanoutConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 不指定名称，表示临时队列
                    exchange = @Exchange(value = "logs", type = "fanout")     // 绑定的交换机
            )
    })
    public void receive(String message) {
        System.out.println("message = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 不指定名称，表示临时队列
                    exchange = @Exchange(value = "logs", type = "fanout")     // 绑定的交换机
            )
    })
    public void receive2(String message) {
        System.out.println("message2 = " + message);
    }

}
