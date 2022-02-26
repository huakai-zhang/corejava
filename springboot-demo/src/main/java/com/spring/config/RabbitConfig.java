package com.spring.config;

import com.spring.mq.RedisConsumer;
//import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class RabbitConfig {

    /**
     * 队列消费者
     */
    /*@Bean
    public RedisConsumer redisConsumer() {
        return new RedisConsumer();
    }

    @Bean("redisExchange")
    public DirectExchange redisExchange() {
        return new DirectExchange("DZ_REDIS_EXCHANGE", true, false, new HashMap<>());
    }

    @Bean("redisQueue")
    public Queue redisQueue() {
        Map<String, Object> map = new HashMap<>();
        // 10 秒钟后成为死信
        map.put("x-message-ttl", 1000 * 60 * 5);
        // 队列中的消息变成死信后，进入死信 交换机
        map.put("x-dead-letter-exchange", "DZ_DEAD_LETTER_EXCHANGE");
        return new Queue("DZ_REDIS_QUEUE", true, false, false, map);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(redisQueue()).to(redisExchange()).with("dz.redis");
    }

    @Bean("deatLetterExchange")
    public TopicExchange deadLetterExchange() {
        return new TopicExchange("DZ_DEAD_LETTER_EXCHANGE", true, false, new HashMap<>());
    }

    @Bean("deatLetterQueue")
    public Queue deadLetterQueue() {
        return new Queue("DZ_DEAD_LETTER_QUEUE", true, false, false, new HashMap<>());
    }

    @Bean public Binding bindingDead() {
        // 无条件路由
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("#");
    }*/
}
