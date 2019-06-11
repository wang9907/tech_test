package com.summer.tech.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TimeClientHandler extends SimpleChannelInboundHandler<String> {

	private byte[] req;

	public TimeClientHandler() {
		req = ("QUERY TIME ORDER " + System.getProperty("line.separator")).getBytes();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("server say:" + msg);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("client active ");
		ByteBuf message = null;
		for (int i = 0; i < 100; i++) {
			message = Unpooled.buffer(req.length);
			message.writeBytes(req);
			ctx.writeAndFlush(message);
		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Client close");
		super.channelInactive(ctx);
	}

}
