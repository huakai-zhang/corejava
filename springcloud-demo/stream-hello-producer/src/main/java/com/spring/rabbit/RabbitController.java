package com.spring.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/16 下午3:22
 */
@RestController
public class RabbitController {

    @Autowired
    private SinkSender sinkSender;

    @GetMapping("/producer")
    public void producer() {
        sinkSender.output().send(MessageBuilder.withPayload("From SinkSender").build());
    }

}
