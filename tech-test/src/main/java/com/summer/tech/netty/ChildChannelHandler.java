package com.summer.tech.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		// 以（"\n"）为结尾分割的 解码器
		//pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		// 字符串解码和编码
		pipeline.addLast("decoder", new StringDecoder());
		// 自己的逻辑handler
		ch.pipeline().addLast(new TimeServerHandler());
	}

}
