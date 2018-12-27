package chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class ChatClientHanlder extends SimpleChannelInboundHandler<String> {

//    @Override
//    public void channelActive(ChannelHandlerContext channelHandlerContext){
//        //System.out.println("channelActive");
//        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("Netty Rocks!", CharsetUtil.UTF_8));
//    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) { //
//        ByteBuf inBuffer = (ByteBuf) msg;
//
//        String received = inBuffer.toString(CharsetUtil.UTF_8);
//        System.out.println("Client received: " + received);
//        ctx.write("hello cc!");
//    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }


}
