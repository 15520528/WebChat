package chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHanlder extends SimpleChannelInboundHandler<String> {
    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //    @Override
    //    public void
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Server join " + channels.size());
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.write("[SERVER]" + incoming.remoteAddress() + " has joinded!\n");
        }
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Server left");
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.write("[SERVER]" + incoming.remoteAddress() + " has left!\n");
        }
        channels.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("message comming");
        Channel incoming = channelHandlerContext.channel();
        for (Channel channel : channels) {
            channel.write("[ " + incoming.remoteAddress() + "] " + s + "\n");
        }
    }
}
