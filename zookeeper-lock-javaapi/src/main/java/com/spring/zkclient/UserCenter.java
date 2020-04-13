package com.spring.zkclient;

import java.io.Serializable;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/13
 */
public class UserCenter implements Serializable {
    private static final long serialVersionUID = 8704729764515013279L;
    // 机器信息
    private int mc_id;

    // 机器名
    private String mc_name;

    public int getMc_id() {
        return mc_id;
    }

    public void setMc_id(int mc_id) {
        this.mc_id = mc_id;
    }

    public String getMc_name() {
        return mc_name;
    }

    public void setMc_name(String mc_name) {
        this.mc_name = mc_name;
    }
}
