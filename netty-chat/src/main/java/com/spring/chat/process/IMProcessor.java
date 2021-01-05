package com.spring.chat.process;

import com.alibaba.fastjson.JSONObject;
import com.spring.chat.protocol.IMDecoder;
import com.spring.chat.protocol.IMEncoder;
import com.spring.chat.protocol.IMMessage;
import com.spring.chat.protocol.IMP;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/5
 */
public class IMProcessor {

    private final static ChannelGroup onlineUsers = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private IMDecoder decoder = new IMDecoder();
    private IMEncoder encoder = new IMEncoder();

    private final AttributeKey<String> NICK_NAME = AttributeKey.valueOf("nickName");
    private final AttributeKey<String> IP_ADDR = AttributeKey.valueOf("ipAddr");
    private final AttributeKey<JSONObject> ATTRS = AttributeKey.valueOf("attrs");

    public void logout(Channel channel) {
        onlineUsers.remove(channel);
    }

    public void process(Channel client, IMMessage msg) {
        process(client, encoder.encode(msg));
    }

    public void process(Channel client, String msg) {
        IMMessage request = decoder.decode(msg);
        if (null == request) {
            return;
        }
        String nickName = request.getSender();
        // 判断如果是登录动作，就往onlineUsers中加入一条信息
        if (IMP.LOGIN.getName().equals(request.getCmd())) {
            client.attr(NICK_NAME).getAndSet(request.getSender());

            onlineUsers.add(client);
            for (Channel channel : onlineUsers) {
                if (channel != client) {
                    request = new IMMessage(IMP.SYSTEM.getName(), systemTime(), onlineUsers.size(), nickName + "加入聊天室！");
                } else {
                    request = new IMMessage(IMP.SYSTEM.getName(), systemTime(), onlineUsers.size(), "已于服务器建立连接！");
                }
                String text = encoder.encode(request);
                channel.writeAndFlush(new TextWebSocketFrame(text));
            }

        } else if (IMP.LOGOUT.getName().equals(request.getCmd())) {
            System.out.println("退出");
            onlineUsers.remove(client);
        } else if (IMP.CHAT.getName().equals(request.getCmd())) {
            for (Channel channel : onlineUsers) {
                if (channel != client) {
                    request.setSender(client.attr(NICK_NAME).get());
                } else {
                    request.setSender("you");
                }
                String text = encoder.encode(request);
                channel.writeAndFlush(new TextWebSocketFrame(text));
            }
        } else if (IMP.FLOWER.getName().equals(request.getCmd())) {
            JSONObject attrs = getAttrs(client);
            long currTime = systemTime();
            if(null != attrs){
                long lastTime = attrs.getLongValue("lastFlowerTime");
                //60秒之内不允许重复刷鲜花
                int secends = 10;
                long sub = currTime - lastTime;
                if(sub < 1000 * secends){
                    request.setSender("you");
                    request.setCmd(IMP.SYSTEM.getName());
                    request.setContent("您送鲜花太频繁," + (secends - Math.round(sub / 1000)) + "秒后再试");
                    String content = encoder.encode(request);
                    client.writeAndFlush(new TextWebSocketFrame(content));
                    return;
                }
            }

            //正常送花
            for (Channel channel : onlineUsers) {
                if (channel == client) {
                    request.setSender("you");
                    request.setContent("你给大家送了一波鲜花雨");
                    setAttrs(client, "lastFlowerTime", currTime);
                }else{
                    request.setSender(client.attr(NICK_NAME).get());
                    request.setContent(client.attr(NICK_NAME).get() + "送来一波鲜花雨");
                }
                request.setTime(systemTime());

                String content = encoder.encode(request);
                channel.writeAndFlush(new TextWebSocketFrame(content));
            }
        }
    }

    public JSONObject getAttrs(Channel client){
        try{
            return client.attr(ATTRS).get();
        }catch(Exception e){
            return null;
        }
    }

    private void setAttrs(Channel client,String key,Object value){
        try{
            JSONObject json = client.attr(ATTRS).get();
            json.put(key, value);
            client.attr(ATTRS).set(json);
        }catch(Exception e){
            JSONObject json = new JSONObject();
            json.put(key, value);
            client.attr(ATTRS).set(json);
        }
    }

    private Long systemTime() {
        return System.currentTimeMillis();
    }
}
