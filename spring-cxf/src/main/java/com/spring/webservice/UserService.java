package com.spring.webservice;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/9
 */
@WebService
@Path(value = "/users/")
public interface UserService {

    @GET
    @Path("/")
    List<User> getUsers();

    @DELETE
    @Path("{id}")
    Response delete(@PathParam("id") int id);

    @POST
    @Path("add")
    Response insert(User user);

    @PUT
    @Path("update")
    Response update(User user);

    @GET
    @Path("{id}")
    User getUser(@PathParam("id") int id);

}
