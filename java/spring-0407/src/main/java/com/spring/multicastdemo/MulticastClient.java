package com.spring.multicastdemo;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/7
 */
public class MulticastClient {
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("224.5.6.7");
            MulticastSocket socket = new MulticastSocket(8888);

            // 加到指定的组
            socket.joinGroup(group);

            byte[] buf = new byte[256];
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
                socket.receive(datagramPacket);

                String msg = new String(datagramPacket.getData());
                System.out.println("接收到的数据：" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
