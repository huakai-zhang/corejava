package com.spring.huakai;

/**
 * @author 春阳
 * @date 2021-02-03 19:30
 */
public class Server2 {
    public static void main(String[] args) {
        new HuaKaiRegistry(8082).start();
    }
}
