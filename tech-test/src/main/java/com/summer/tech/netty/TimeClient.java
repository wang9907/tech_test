package com.summer.tech.netty;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {

	public static String host = "127.0.0.1";
	public static int port = 8080;

	public static void main(String[] args) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new TimeClientInitializer());

			// 连接服务端
			Channel ch = b.connect(host, port).sync().channel();

			// 控制台输入
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			for (;;) {
				String line = in.readLine();
				if (line == null) {
					continue;
				}
				/**
				 * 向服务器发送在控制台输入的文本，并用"\r\n"结尾
				 * 之所以用\r\n结尾，是因为就我们在handler中添加了DelimiterBasedFrameDecoder帧解码器
				 * 这个解码器是一个根据\n分隔符的解码器，所以每条消息的最后必须加上\n否则无法识别和解码
				 */
				ch.writeAndFlush(line + "\r\n");
			}
		} finally {
			group.shutdownGracefully();
		}
	}
}
