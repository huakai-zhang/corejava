package com.nio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Future;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/3
 */
public class AIOServer {

    private int port = 8080;

    public AIOServer(int port) {
        this.port = port;
    }

    public void listen() throws IOException {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
        server.bind(new InetSocketAddress(this.port));

        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                result.read(buffer);
                buffer.flip();
                System.out.println(new String(buffer.array()));
            }

            @Override
            public void failed(Throwable exc, Object attachment) {

            }
        });

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new AIOServer(8080).listen();
    }
}
