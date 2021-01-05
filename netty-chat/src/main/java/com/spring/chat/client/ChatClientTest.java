package com.spring.chat.client;

import com.spring.chat.client.handler.ChatClientHandler;
import com.spring.chat.protocol.IMDecoder;
import com.spring.chat.protocol.IMEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.io.IOException;


/**
 * 客户端
 * @author Tom
 *
 */
public class ChatClientTest {

    private ChatClientHandler clientHandler;
    private String host;
    private int port;

    public ChatClientTest(String nickName){
    		this.clientHandler = new ChatClientHandler(nickName);
    }
    
    public void connect(String host,int port){
    		this.host = host;
    		this.port = port;

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast("freameDecoder", new LengthFieldBasedFrameDecoder(65536, 0, 2));
                    //ch.pipeline().addLast("UserDecoder", new UserDecoder());
                }
            });
            ChannelFuture f = b.connect(this.host, this.port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
    
    
    public static void main(String[] args) throws IOException{
		new ChatClientTest("Admin").connect("localhost",80);
    }
    
}
