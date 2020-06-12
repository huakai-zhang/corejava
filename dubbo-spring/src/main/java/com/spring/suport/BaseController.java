package com.spring.suport;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/12
 */
public class BaseController {
    ThreadLocal<String> uidLocal = new ThreadLocal<>();

    public String getUid() {
        return uidLocal.get();
    }

    public void setUid(String uid) {
        uidLocal.set(uid);
    }
}
