package com.spring.discovery;

import java.util.List;
import java.util.Random;

/**
 * @author 春阳
 * @date 2021-02-04 14:43
 */
public class RandomLoadBalance extends AbstractLoadBalanceStrategy {
    @Override
    protected String doSelect(List<String> repos) {
        String serverAddress = repos.get(new Random().nextInt(repos.size()));
        System.out.println("调用 " + serverAddress + " 成功");
        return serverAddress;
    }
}
