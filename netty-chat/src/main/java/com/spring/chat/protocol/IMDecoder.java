package com.spring.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.msgpack.MessagePack;
import org.msgpack.MessageTypeException;

/**
 * 自定义IM协议的编码器
 */
public class IMDecoder extends ByteToMessageDecoder {

	//解析IM写一下请求内容的正则
	private Pattern pattern = Pattern.compile("^\\[(.*)\\](\\s\\-\\s(.*))?");
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,List<Object> out) throws Exception {
		System.out.println("解码");
		try {
			final int length = in.readableBytes();
			final byte[] array = new byte[length];
			System.out.println("1");
			// 变为一个字符串
			String content = new String(array, in.readableBytes(), length);
			if (!(null == content || "".equals(content.trim()))) {
				if (!IMP.isIMP(content)) {
					ctx.channel().pipeline().remove(this);
					return;
				}
			}
			System.out.println("2");
			// 反序列化的过程
			in.getBytes(in.readerIndex(), array, 0, length);
			// 把字节转为MessagePack，然后在把这个MessagePack转为IMMessage
			out.add(new MessagePack().read(array, IMMessage.class));
			in.clear();
			System.out.println("3");
		} catch (Exception e) {
			ctx.channel().pipeline().remove(this);
		}
	}
	
	/**
	 * 字符串解析成自定义即时通信协议
	 * @param msg
	 * @return
	 */
	public IMMessage decode(String msg){
		if(null == msg || "".equals(msg.trim())){ return null; }
		try{
			Matcher m = pattern.matcher(msg);
			String header = "";
			String content = "";
			if(m.matches()){
				header = m.group(1);
				content = m.group(3);
			}
			
			String [] heards = header.split("\\]\\[");
			long time = 0;
			try{ time = Long.parseLong(heards[1]); } catch(Exception e){}
			String nickName = heards[2];
			//昵称最多十个字
			nickName = nickName.length() < 10 ? nickName : nickName.substring(0, 9);
			
			if(msg.startsWith("[" + IMP.LOGIN.getName() + "]")){
				return new IMMessage(heards[0],time,nickName);
			}else if(msg.startsWith("[" + IMP.CHAT.getName() + "]")){
				return new IMMessage(heards[0],time,nickName,content);
			}else if(msg.startsWith("[" + IMP.FLOWER.getName() + "]")){
				return new IMMessage(heards[0],time,nickName);
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
