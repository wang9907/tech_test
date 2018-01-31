package com.summer.tech.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelTest {

	public static void main(String[] args) {
		ServerSocketChannel serverSocketChannel;
		try {
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(9999));
			serverSocketChannel.configureBlocking(false);
			while (true) {
				SocketChannel socketChannel = serverSocketChannel.accept();
				if (socketChannel != null) {
					// do something with socketChannel...
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
