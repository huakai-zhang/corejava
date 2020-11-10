package com.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig {

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        log.debug("start taskExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);//核心线程数
        executor.setMaxPoolSize(4);//最大线程数
        executor.setQueueCapacity(4);//队列最大长度 >=mainExecutor.maxSize
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        executor.setKeepAliveSeconds(300);//线程池维护线程所允许的空闲时间
        executor.initialize();
        return executor;
    }

}
