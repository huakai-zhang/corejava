package com.spring.discovery;

import java.util.List;

/**
 * @author 春阳
 * @date 2021-02-04 14:21
 */
public interface LoadBalanceStrategy {
    String selectHost(List<String> repos);
}
