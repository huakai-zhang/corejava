package com.spring.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/23 上午11:23
 */
@Path("/say")
public interface ISayHelloService {

    @GET
    @Path("/hello")
    String sayHello();
}
