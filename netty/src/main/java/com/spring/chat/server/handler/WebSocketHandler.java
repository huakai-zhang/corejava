package com.spring.chat.server.handler;

import com.spring.chat.process.IMProcessor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/5
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private IMProcessor processor = new IMProcessor();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        processor.process(ctx.channel(), msg.text());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        processor.logout(ctx.channel());
    }
}
