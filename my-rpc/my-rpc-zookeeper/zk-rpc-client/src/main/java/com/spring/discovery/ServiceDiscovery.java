package com.spring.discovery;

/**
 * @author 春阳
 * @date 2021-02-04 14:23
 */
public interface ServiceDiscovery {
    //根据服务名称返回服务地址
    String  discovery(String serviceName);
}
