package com.spring.discovery;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 春阳
 * @date 2021-02-04 16:38
 */
public class RoundRobinLocalBalance extends AbstractLoadBalanceStrategy {

    private static AtomicInteger count = new AtomicInteger();

    @Override
    protected String doSelect(List<String> repos) {
        count.incrementAndGet();
        int num = count.intValue();
        String serverAddress = repos.get(num % repos.size());
        System.out.println("调用 " + serverAddress + " 成功");
        return serverAddress;
    }
}
