package com.spring.controller;

import com.spring.dubbo.order.DoOrderRequest;
import com.spring.dubbo.order.IOrderService;
import com.spring.dubbo.user.IUserService;
import com.spring.dubbo.user.dto.UserResponse;
import com.spring.services.CustomerService;
import com.spring.suport.Anonymous;
import com.spring.suport.BaseController;
import com.spring.suport.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

//    @Autowired
//    private CustomerService customerService;

    // 按名称注入，变量名需要与api.jar提供的id相同
    //@Autowired
    //IOrderService orderServiceSpring;
//
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/index")
    @Anonymous
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @PostMapping(value = "/login")
    @Anonymous
    public @ResponseBody String login(HttpServletResponse httpServletResponse){
        DoOrderRequest request = new DoOrderRequest();
        request.setName("dubbo-spring");
        //orderServiceSpring.doOrder(request);
        UserResponse response = userService.login("spring", "1234");
        //customerService.insert();
        if ("11111".equals(response.getCode())) {
            httpServletResponse.addHeader("Set-Cookie", "access_token=" + response.getToken() + ";Path=/;HttpOnly");
        }
        return "000000";
    }

    @RequestMapping(value = "/hello")
    public ModelAndView hello(){
        return new ModelAndView("hello");
    }
}
