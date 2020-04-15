package com.spring.dubbo.order;

import org.springframework.stereotype.Service;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/14
 */
@Service(value = "orderService2")
public class OrderServiceImpl2 implements IOrderService {
    @Override
    public DoOrderResponse doOrder(DoOrderRequest request) {
        System.out.println("曾经来过2.0：" + request);
        DoOrderResponse response = new DoOrderResponse();
        response.setCode("11111");
        response.setMemo("处理成功");
        return response;
    }
}
