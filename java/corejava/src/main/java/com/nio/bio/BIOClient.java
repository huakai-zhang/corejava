package com.nio.bio;

import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class BIOClient {
    public static void main(String[] args) {
        int count = 1000;

        final CountDownLatch latch = new CountDownLatch(count);
        for (int i =0; i < count; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        latch.await();

                        Socket client = new Socket("localhost", 8080);
                        OutputStream os = client.getOutputStream();
                        String name = UUID.randomUUID().toString();

                        os.write(name.getBytes());
                        os.close();
                        client.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            latch.countDown();
        }

    }
}
