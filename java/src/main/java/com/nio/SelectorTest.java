package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/3
 */
public class SelectorTest {

    /**
     * 注册事件
     */
    private Selector getSelector() throws IOException {
        // 创建 Selector 对象
        Selector sel = Selector.open();
        // 创建可选择通道，并配置为非阻塞模式
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        // 绑定通道到指定端口
        ServerSocket socket = server.socket();
        InetSocketAddress address = new InetSocketAddress(8080);
        socket.bind(address);
        // 向 Selector 中注册感兴趣事件
        server.register(sel, SelectionKey.OP_ACCEPT);
        return sel;
    }

}
