package com.spring.rmi.myself;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/9
 */
public class UserServer extends User {
    public static void main(String[] args) {
        UserServer userServer = new UserServer();
        userServer.setAge(18);

        User_Skeleton skeleton = new User_Skeleton(userServer);
        skeleton.start();
    }
}
