package com.spring.webservice;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/9
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUsers() {
        return Storage.users;
    }

    @Override
    public Response delete(int id) {
        Storage.users.remove(id);
        Response response = new Response();
        response.setCode("00");
        response.setMsg("success");
        return response;
    }

    @Override
    public Response insert(User user) {
        return null;
    }

    @Override
    public Response update(User user) {
        return null;
    }

    @Override
    public User getUser(int id) {
        return Storage.users.get(id);
    }
}
