package com.spring.dubbo.order;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/14
 */
public interface IOrderService {

    /**
     * 下单操作
     * @param request
     * @return
     */
    DoOrderResponse doOrder(DoOrderRequest request);

}
