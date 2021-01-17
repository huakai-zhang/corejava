package com.spring.rabbit;

import org.springframework.cloud.stream.annotation.EnableBinding;
/**
 * @author 春阳
 * @date 2021-01-15 18:48
 */
@EnableBinding({SinkSender.class})
public class SinkReceiver {

}
