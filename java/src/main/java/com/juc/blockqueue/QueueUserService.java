package com.juc.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 春阳
 * @date 2020-12-30 13:45
 */
public class QueueUserService {
    private final ExecutorService single = Executors.newSingleThreadExecutor();
    private volatile boolean isRunning = true;
    ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(10);

    {
        init();
    }

    public void init(){
        single.execute(()->{
            while(isRunning){
                try {
                    User user = (User) arrayBlockingQueue.take();//阻塞的方式获取队列中的数据
                    sendPoints(user);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean register(){
        User user=new User();
        user.setName("Mic");
        addUser(user);
        arrayBlockingQueue.add(user);//添加到异步队列
        return true;
    }

    public static void main(String[] args) {
        if(new QueueUserService().register()) {
            System.out.println("添加用户完成");
        }
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
        System.out.println("积分发送完毕");
    }
}
