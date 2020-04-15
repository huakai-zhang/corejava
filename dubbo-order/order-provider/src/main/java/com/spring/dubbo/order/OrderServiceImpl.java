package com.spring.dubbo.order;

import org.springframework.stereotype.Service;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/14
 */
@Service(value = "orderService")
public class OrderServiceImpl implements IOrderService {
    @Override
    public DoOrderResponse doOrder(DoOrderRequest request) {
        System.out.println("曾经来过：" + request);
        DoOrderResponse response = new DoOrderResponse();
        response.setCode("1000");
        response.setMemo("处理成功");
        return response;
    }
}