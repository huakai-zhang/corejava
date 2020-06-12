package com.spring.dubbo.utils;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/12
 */
public class JwtInfo {

    private String uid;

    public JwtInfo(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
