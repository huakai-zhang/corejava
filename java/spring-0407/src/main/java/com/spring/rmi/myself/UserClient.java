package com.spring.rmi.myself;

import java.io.IOException;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/9
 */
public class UserClient {
    public static void main(String[] args) throws IOException {
        User user = new User_Stub();

        System.out.println(user.getAge());
    }
}
