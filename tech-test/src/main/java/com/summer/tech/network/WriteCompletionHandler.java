package com.summer.tech.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class WriteCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
	private AsynchronousSocketChannel channel;

	public WriteCompletionHandler(AsynchronousSocketChannel channel) {
		this.channel = channel;
	}

	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		System.out.println("swriteresult:"+result);
		// 如果没有发送完成，继续发送
		if (attachment.hasRemaining()) {
			channel.write(attachment, attachment, this);
		}
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		exc.printStackTrace();
		try {
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
