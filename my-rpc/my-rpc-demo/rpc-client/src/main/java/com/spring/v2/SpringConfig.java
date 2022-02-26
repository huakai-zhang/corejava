package com.spring.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/1 下午1:46
 */
@Configuration
public class SpringConfig {

    @Bean(name = "rpcProxyClient")
    public RpcProxyClient rpcProxyClient() {
        return new RpcProxyClient();
    }

}
