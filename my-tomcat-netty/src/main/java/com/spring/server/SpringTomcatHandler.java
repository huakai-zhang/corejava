package com.spring.server;

import com.spring.http.SpringRequest;
import com.spring.http.SpringResponse;
import com.spring.servlet.MyServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

public class SpringTomcatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest r = (HttpRequest) msg;

            SpringRequest request = new SpringRequest(ctx, r);
            SpringResponse response = new SpringResponse(ctx, r);

            new MyServlet().doGet(request, response);
        }
        // super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
