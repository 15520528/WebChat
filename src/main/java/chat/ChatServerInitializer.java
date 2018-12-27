package chat;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChatServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel arg0) throws Exception{
        ChannelPipeline pipeline = arg0.pipeline();
        pipeline.addLast(new HttpRequestDecoder(4096, 8192, 8192, false));
        pipeline.addLast(new HttpResponseEncoder());
//        pipeline.addLast( new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
//        pipeline.addLast( new StringDecoder());
//        pipeline.addLast(new StringEncoder());

        pipeline.addLast(new DiscardServerHandler());
    }
}
