package com.spring.controller;

import com.spring.dubbo.order.DoOrderRequest;
import com.spring.dubbo.order.IOrderService;
import com.spring.dubbo.user.IUserService;
import com.spring.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

//    @Autowired
//    private CustomerService customerService;

    // 按名称注入，变量名需要与api.jar提供的id相同
    @Autowired
    IOrderService orderServiceSpring;
//
//    @Autowired
//    IUserService userService;

    @RequestMapping(value = "/index")
    public ModelAndView index(){
        DoOrderRequest request = new DoOrderRequest();
        request.setName("dubbo-spring");
        orderServiceSpring.doOrder(request);
        //userService.login("spring", "1234");
        //customerService.insert();
        return new ModelAndView("index");
    }

}
