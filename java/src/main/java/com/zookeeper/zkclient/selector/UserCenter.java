package com.zookeeper.zkclient.selector;

import java.io.Serializable;

/**
 * @author 春阳
 * @date 2020-12-23 15:59
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

    @Override
    public String toString() {
        return "UserCenter{" +
                "mc_id=" + mc_id +
                ", mc_name='" + mc_name + '\'' +
                '}';
    }
}
