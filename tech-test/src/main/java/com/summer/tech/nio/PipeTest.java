package com.summer.tech.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeTest {

	public static void main(String[] args) {
		try {
			// 向管道写数据
			Pipe pipe = Pipe.open();
			Pipe.SinkChannel sinkChannel = pipe.sink();
			String newData = "New String to write to file..." + System.currentTimeMillis();
			ByteBuffer buf = ByteBuffer.allocate(48);
			buf.clear();
			buf.put(newData.getBytes());

			buf.flip();

			while (buf.hasRemaining()) {
				sinkChannel.write(buf);
			}

			// 从管道读取数据
			Pipe.SourceChannel sourceChannel = pipe.source();

			ByteBuffer rbuf = ByteBuffer.allocate(48);
			int bytesRead = sourceChannel.read(rbuf);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
