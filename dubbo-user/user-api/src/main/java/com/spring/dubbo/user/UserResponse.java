package com.spring.dubbo.user;

import java.io.Serializable;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/16
 */
public class UserResponse implements Serializable {
    private static final long serialVersionUID = -6035423593460145840L;

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
