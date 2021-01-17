package com.spring.rabbit;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/16 下午12:54
 */
public interface SinkSender {
    @Output(Source.OUTPUT)
    MessageChannel output();
}
