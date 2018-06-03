package com.summer.tech.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

public class AIOClient {

	private static final int PORT = 3002;

	public static void main(String[] args) throws IOException {
		ByteBuffer buff = ByteBuffer.allocate(1024);
		Charset utf = Charset.forName("UTF-8");
		AsynchronousSocketChannel clientChannel = AsynchronousSocketChannel
				.open();
		try {
			clientChannel.connect(new InetSocketAddress("127.0.0.1", PORT))
					.get();
			buff.clear();
			clientChannel.read(buff).get();
			buff.flip();
			String content = utf.decode(buff).toString();
			System.out.println("服务器信息：" + content);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
