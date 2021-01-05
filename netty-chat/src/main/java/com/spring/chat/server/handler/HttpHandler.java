package com.spring.chat.server.handler;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/6/5
 */
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    // classPath
    private URL baseURL = HttpHandler.class.getProtectionDomain().getCodeSource().getLocation();

    private final String WEB_ROOT = "webroot";

    private File getFileFromRoot(String fileName) throws Exception {
        String path = baseURL.toURI() + WEB_ROOT + "/" + fileName;
        path = !path.startsWith("file:") ? path : path.substring(5);
        path = path.replace("//", "/");
        return new File(path);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) throws Exception {
        // 获取到了客户端请求的uri
        String uri = request.uri();

        String page = uri.equals("/") ? "chat.html" : uri;
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(getFileFromRoot(page), "r");
        } catch (Exception e) {
            channelHandlerContext.fireChannelRead(request.retain());
            return;
        }
        String contextType = "text/html;";

        if (uri.endsWith(".css")) {
            contextType = "text/css;";
        } else if (uri.endsWith(".js")) {
            contextType = "text/javascript;";
        } else if (uri.toLowerCase().matches("(jpg|png|gif)$")) {
            String ext = uri.substring(uri.lastIndexOf("."));
            contextType = "image/" + ext + ";";
        }

        HttpResponse response = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, contextType + "charset=utf-8;");

        boolean keepAlive = HttpHeaders.isKeepAlive(request);
        if (keepAlive) {
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, file.length());
            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        }

        channelHandlerContext.write(response);
        channelHandlerContext.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));

        // 清空缓冲区
        ChannelFuture f = channelHandlerContext.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);

        if (!keepAlive) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
        file.close();
    }

}
