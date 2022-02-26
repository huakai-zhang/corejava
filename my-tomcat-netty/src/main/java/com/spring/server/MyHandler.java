package com.spring.server;

import com.spring.http.SpringRequest;
import com.spring.http.SpringResponse;
import com.spring.servlet.MyServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/9 下午12:52
 */
public class MyHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("哈哈");
        ctx.fireChannelRead(msg);
    }
}
