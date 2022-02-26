package com.nio.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class BIOClient {
    public static void main(String[] args) {
        int count = 10;

        final CountDownLatch latch = new CountDownLatch(count);
        for (int i =0; i < count; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        latch.await();

                        Socket client = new Socket("localhost", 8888);
                        System.out.println("连接成功");
                        Thread.sleep(10000);
                        OutputStream os = client.getOutputStream();
                        String name = UUID.randomUUID().toString();
                        os.write(name.getBytes());
                        os.close();
                        client.close();
                        System.out.println("发送成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            latch.countDown();
        }
        /*try {
            Socket client = new Socket("localhost", 8080);
            OutputStream os = client.getOutputStream();
            String name = UUID.randomUUID().toString();
            os.write(name.getBytes());
            os.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
