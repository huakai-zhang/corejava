package com.spring.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * @author 春阳
 * @date 2021-01-15 18:48
 */
@EnableBinding({Sink.class})//, SinkSender.class})
public class SinkReceiver {

    private Logger log = LoggerFactory.getLogger(SinkReceiver.class);

    @StreamListener(Sink.INPUT)
    // 消息反馈
    //@SendTo("output2")
    public void receive(String message) {
        log.info("Received: {}", message);
        //return "消息反馈";
    }

    //@StreamListener("input2")
    public void process2(String message) {
        log.info("Received2: {}", message);
    }
}
