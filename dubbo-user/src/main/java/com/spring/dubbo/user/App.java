package com.spring.dubbo.user;

import com.spring.dubbo.order.DoOrderRequest;
import com.spring.dubbo.order.DoOrderResponse;
import com.spring.dubbo.order.IOrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("order-consumer.xml");

        // 用户下单过程
        IOrderService service = (IOrderService) context.getBean("orderService");

        DoOrderRequest request = new DoOrderRequest();
        request.setName("spring");
        DoOrderResponse response = service.doOrder(request);
        System.out.println(response);
    }
}
