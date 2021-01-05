package com.spring.chat.server.handler;

import com.spring.chat.process.IMProcessor;
import com.spring.chat.protocol.IMMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/5
 */
public class SocketHandler extends SimpleChannelInboundHandler<IMMessage> {

    private IMProcessor processor = new IMProcessor();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IMMessage msg) throws Exception {
        System.out.println("发送消息");
        processor.process(ctx.channel(), msg);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有客户端链接");
        super.handlerAdded(ctx);
    }
}
