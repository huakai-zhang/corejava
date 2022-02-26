package com.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

/**
 * @author 春阳
 * @date 2020-12-04 18:12
 */
public class WebSocketDemo extends WebSocketClient {
    public WebSocketDemo(String url) throws URISyntaxException {
        super(new URI(url));
    }

    @Override
    public void onOpen(ServerHandshake shake) {
        System.out.println("握手...");
    }

    @Override
    public void onMessage(String paramString) {
        System.out.println("接收到消息："+paramString);
    }

    @Override
    public void onClose(int paramInt, String paramString, boolean paramBoolean) {
        System.out.println("关闭...");
    }

    @Override
    public void onError(Exception e) {
        System.out.println("异常"+e);

    }

    public static void main(String[] args) {
        try {
            WebSocketClient client = new WebSocketDemo("ws://113.31.103.180:9532/webSocket/chunyang");
            client.connect();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*for (int i = 0; i < 10; i++) {
                client.reconnect();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
