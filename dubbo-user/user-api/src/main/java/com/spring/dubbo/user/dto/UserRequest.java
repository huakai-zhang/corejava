package com.spring.dubbo.user.dto;

import java.io.Serializable;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/12
 */
public class UserRequest implements Serializable {
    private static final long serialVersionUID = -2306567474410828115L;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
