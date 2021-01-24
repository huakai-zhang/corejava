package com.spring;

import com.spring.service.LoginService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/22 下午8:40
 */
public class ClientApplication {
    public static void main( String[] args ){
        ClassPathXmlApplicationContext classPathXmlApplicationContext= new ClassPathXmlApplicationContext(new String[]{"application.xml"});
        LoginService loginService = (LoginService)classPathXmlApplicationContext.getBean("loginService");
        System.out.println(loginService.login("admin","admin"));
    }
}
