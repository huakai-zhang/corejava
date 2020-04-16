package com.spring.dubbo.order;

import com.spring.dubbo.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/14
 */
@Service(value = "orderService2")
public class OrderServiceImpl2 implements IOrderService {

    @Autowired
    IUserService userService;

    @Override
    public DoOrderResponse doOrder(DoOrderRequest request) {
        userService.toLogin("order模块");
        System.out.println("曾经来过2.0：" + request);
        DoOrderResponse response = new DoOrderResponse();
        response.setCode("11111");
        response.setMemo("处理成功");
        return response;
    }
}
