package com.spring;

import com.spring.service.LoginService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 春阳
 * @date 2021-01-22 12:26
 */
public class ClientApplication {
    public static void main( String[] args ){
        ClassPathXmlApplicationContext classPathXmlApplicationContext= new ClassPathXmlApplicationContext(new String[]{"application.xml"});
        LoginService loginService=(LoginService)classPathXmlApplicationContext.getBean("loginService");
        System.out.println(loginService.login("admin","admin"));
    }
}