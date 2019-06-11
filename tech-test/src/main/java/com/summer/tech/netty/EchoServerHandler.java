package com.summer.tech.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoServerHandler extends SimpleChannelInboundHandler<String> {

	int counter = 0;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		String body = msg;
		System.out.println("this is " + ++counter + " times receive client:" + msg);
		body += "$_";
		ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
		ctx.writeAndFlush(echo);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();// 发生异常，关闭链路
	}
	
}
