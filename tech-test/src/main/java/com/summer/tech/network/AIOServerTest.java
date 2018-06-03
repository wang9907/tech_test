package com.summer.tech.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AIOServerTest {

	private static final int PORT = 3002;
	
	public static void main(String[] args) throws IOException {
		AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
		serverChannel.bind(new InetSocketAddress(PORT));
		while(true){
			Future<AsynchronousSocketChannel> future = serverChannel.accept();
			//获取连接成功之后的AsynchronousSocketChannel
			try {
				AsynchronousSocketChannel socketChannel = future.get();
				socketChannel.write(ByteBuffer.wrap("你好，我是来自外星人".getBytes("UTF-8"))).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}
