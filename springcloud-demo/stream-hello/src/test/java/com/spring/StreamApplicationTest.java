package com.spring;

import com.spring.rabbit.SinkSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/16 下午12:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StreamApplication.class)
@WebAppConfiguration
public class StreamApplicationTest {

    //@Autowired
    //private SinkSender sinkSender;

    @Test
    public void test() {
        //sinkSender.output().send(MessageBuilder.withPayload("From SinkSender").build());
    }

}
