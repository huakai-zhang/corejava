package com.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 春阳
 * @date 2020-12-15 12:10
 */
public class MoreServer extends Thread {

    //与服务端连接的所有客户端集合
    private List<Socket> socketList;
    //本客户端
    private Socket socket;

    public MoreServer(List<Socket> socketList, Socket socket) {
        this.socketList = socketList;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //接收数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String message = reader.readLine();
                System.out.println("收到消息：" + message);
                //广播数据
                for (Socket s : socketList) {
                    if (this.socket != s) {
                        PrintWriter writer = new PrintWriter(s.getOutputStream());
                        writer.println("收到消息：" + message);
                        writer.flush();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        try {
            //保存有所有的客户端Socket连接
            List<Socket> socketList = new ArrayList<Socket>();
            // 创建一个 ServerSocket 监听客户请求
            ServerSocket server = new ServerSocket(8888);
            Socket socket = null;

            while (true) {
                socket = server.accept();
                //将连接的客户端加入到集合中
                socketList.add(socket);
                System.out.println("有客户端上线");
                //创建一个线程用来处理和客户端的通信
                new MoreServer(socketList, socket).start();
            }
        } catch (Exception e) {
            System.out.println("Error. " + e);
        }
    }
}