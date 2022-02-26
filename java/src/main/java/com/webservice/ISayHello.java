package com.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/9
 */
// SEI和SEI的实现类
@WebService
public interface ISayHello {
    // SEI中的方法
    @WebMethod
    String sayHello(String name);
}
