package com.spring.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author 春阳
 * @date 2021-01-22 12:09
 */
@Path("/login")
public interface LoginService {

    @GET
    @Path("/{username}")
    String login(@PathParam("username") String username, String password);
}
