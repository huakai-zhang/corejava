package com.juc.blockqueue;

/**
 * @author 春阳
 * @date 2020-12-30 13:42
 */
public class UserService {
    public boolean register(){
        User user=new User();
        user.setName("Mic");
        addUser(user);
        sendPoints(user);
        return true;
    }

    private void addUser(User user){
        System.out.println("添加用户："+user);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendPoints(User user){
        System.out.println("发送积分给指定用户:"+user);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UserService().register();
    }
}
