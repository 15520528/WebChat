package chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

public class DiscardServerHandler extends SimpleChannelInboundHandler<String> {
    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    WebSocketServerHandshaker handshaker;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof TextWebSocketFrame) {
            System.out.println(((TextWebSocketFrame) msg).text());
            ctx.channel().writeAndFlush(
                        new TextWebSocketFrame("Message recieved : " + ((TextWebSocketFrame) msg).text()));
            return ;
        }
        DefaultHttpRequest httpRequest = null;
        if (msg instanceof DefaultHttpRequest)
        {
            httpRequest = (DefaultHttpRequest) msg;

            // Handshake
            WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://127.0.0.1:8081/", null, false);
            final Channel channel = ctx.channel();
            final WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(httpRequest);

            if (handshaker == null) {

            } else {
                ChannelFuture handshake = handshaker.handshake(channel, httpRequest);
            }
        }
//        if (msg instanceof HttpRequest) {
//
//            HttpRequest httpRequest = (HttpRequest) msg;
//
//            System.out.println("Http Request Received");
//
//            HttpHeaders headers = httpRequest.headers();
//            System.out.println("Connection : " +headers.get("Connection"));
//            System.out.println("Upgrade : " + headers.get("Upgrade"));
//
//            if ("Upgrade".equalsIgnoreCase(headers.get(HttpHeaderNames.CONNECTION)) &&
//                    "WebSocket".equalsIgnoreCase(headers.get(HttpHeaderNames.UPGRADE))) {
//
//                //Adding new handler to the existing pipeline to handle WebSocket Messages
//                ctx.pipeline().replace(this, "websocketHandler", new WebSocketHandler());
//
//                System.out.println("WebSocketHandler added to the pipeline");
//                System.out.println("Opened Channel : " + ctx.channel());
//                System.out.println("Handshaking....");
//                //Do the Handshake to upgrade connection from HTTP to WebSocket protocol
//                handleHandshake(ctx, httpRequest);
//                System.out.println("Handshake is done");
//            }
//        } else {
//            System.out.println("Incoming request is unknown");
//        }
    }
    
        /* Do the handshaking for WebSocket request */
    protected void handleHandshake(ChannelHandlerContext ctx, HttpRequest req) {
        WebSocketServerHandshakerFactory wsFactory =
                new WebSocketServerHandshakerFactory(getWebSocketURL(req), null, true);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    protected String getWebSocketURL(HttpRequest req) {
        System.out.println("Req URI : " + req.getUri());
        String url =  "ws://" + req.headers().get("Host") + req.getUri() ;
        System.out.println("Constructed URL : " + url);
        return url;
    }
    
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        TextWebSocketFrame frame = new TextWebSocketFrame(msg);
        System.out.println(frame.text());
        for (Channel c: channels) {
            if (c != ctx.channel()) {
                c.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + msg + '\n');
            } else {
                c.writeAndFlush("[you] " + msg + '\n');
            }
        }
    }

    //client session joined into this chat
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        channels.add(ctx.channel());
        System.out.println("đm 1 thằng client đã tham gia >< ");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}

//public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
//    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
//        ByteBuf inBuffer = (ByteBuf) msg;
//
//        String received = inBuffer.toString(CharsetUtil.UTF_8);
//        System.out.println("Server received: " + received);
//
//        //ctx.write(Unpooled.copiedBuffer("Hello thằng lol " + received, CharsetUtil.UTF_8));
//        System.out.println(channels.size());
//        for (Channel channel : channels) {
//            channel.write("Hello mấy thằng lol ");
//        }
//    }
//
//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) {
//        channels.add(ctx.channel());
//        System.out.println("đm 1 thằng client đã tham gia >< ");
//    }
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
//                .addListener(ChannelFutureListener.CLOSE);
//    }
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
//        // Close the connection when an exception is raised.
//        cause.printStackTrace();
//        ctx.close();
//    }
//}
