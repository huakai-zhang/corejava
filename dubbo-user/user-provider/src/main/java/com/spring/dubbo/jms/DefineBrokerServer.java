package com.spring.dubbo.jms;

import org.apache.activemq.broker.BrokerService;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/8
 */
public class DefineBrokerServer {
    public static void main(String[] args) {
        BrokerService brokerService = new BrokerService();
        try {
            brokerService.setUseJmx(true);
            brokerService.addConnector("tcp://localhost:61616");
            brokerService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
