package com.summer.tech.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelTest {

	public static void main(String[] args) {
		DatagramChannel channel;
		try {
			channel = DatagramChannel.open();

			channel.socket().bind(new InetSocketAddress(9999));

			ByteBuffer buf = ByteBuffer.allocate(48);
			buf.clear();
			channel.receive(buf);

			String newData = "New String to write to file..." + System.currentTimeMillis();
			ByteBuffer wbuf = ByteBuffer.allocate(48);
			buf.clear();
			buf.put(newData.getBytes());
			buf.flip();

			int bytesSent = channel.send(wbuf, new InetSocketAddress("jenkov.com", 80));

			channel.connect(new InetSocketAddress("jenkov.com", 80));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
