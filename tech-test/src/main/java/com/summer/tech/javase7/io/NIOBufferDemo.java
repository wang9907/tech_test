package com.summer.tech.javase7.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIOBufferDemo {

	public static void main(String[] args) throws IOException {
		NIOBufferDemo demo = new NIOBufferDemo();
		// demo.useByteBuffer();
		// demo.byteOrder();
		// demo.compact();
		// demo.viewBuffer();
		// demo.openAndWrite();
		// demo.readWriteAbsolute();
		demo.loadWebPage("http://www.baidu.com");
	}

	public void useByteBuffer() {
		ByteBuffer buffer = ByteBuffer.allocate(32);
		buffer.put((byte) 1);
		buffer.put(new byte[3]);
		buffer.putChar('A');
		buffer.putFloat(0.11f);
		buffer.putLong(10, 100L);

		System.out.println(buffer.getChar(4));
	}

	public void byteOrder() {
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(1);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		System.out.println(buffer.getInt(0));
	}

	public void compact() {
		ByteBuffer buffer = ByteBuffer.allocate(32);
		buffer.put(new byte[16]);
		buffer.flip();
		buffer.getInt();
		buffer.compact();
		int pos = buffer.position();
		System.out.println(pos);
	}

	public void viewBuffer() {
		ByteBuffer buffer = ByteBuffer.allocate(32);
		buffer.putInt(1);
		IntBuffer intBuffer = buffer.asIntBuffer();
		intBuffer.put(2);
		int value = buffer.getInt();
		System.out.println(value);
	}

	public void openAndWrite() throws IOException {
		FileChannel channel = FileChannel.open(Paths.get("my.txt"),
				StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		ByteBuffer buffer = ByteBuffer.allocate(64);
		buffer.putChar('A').flip();
		channel.write(buffer);
	}

	public void readWriteAbsolute() throws IOException {
		FileChannel channel = FileChannel.open(Paths.get("absolute.txt"),
				StandardOpenOption.CREATE, StandardOpenOption.READ,
				StandardOpenOption.WRITE);
		ByteBuffer writeBuffer = ByteBuffer.allocate(4).putChar('A')
				.putChar('B');
		writeBuffer.flip();
		channel.write(writeBuffer, 1024);
		ByteBuffer readBuffer = ByteBuffer.allocate(2);
		channel.read(readBuffer, 1026);
		readBuffer.flip();
		char result = readBuffer.getChar();
		System.out.println(result);
	}

	// 使用文件通道保存网页内容
	public void loadWebPage(String url) throws IOException {
		try (FileChannel destChannel = FileChannel.open(
				Paths.get("content.txt"), StandardOpenOption.CREATE,
				StandardOpenOption.WRITE)) {
			InputStream input = new URL(url).openStream();
			ReadableByteChannel srcChannel = Channels.newChannel(input);
			destChannel.transferFrom(srcChannel, 0, Integer.MAX_VALUE);
		}
	}

	// 使用字节缓冲区进行文件复制
	public void copyUseByteBuffer() throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(32 * 1024);
		try (FileChannel src = FileChannel.open(Paths.get("srcFile.txt"),
				StandardOpenOption.READ)) {
			FileChannel dest = FileChannel.open(Paths.get("descFile.txt"),
					StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			while (src.read(buffer) > -1 || buffer.position() != 0) {
				buffer.flip();
				dest.write(buffer);
				buffer.compact();
			}
		}
	}

	// 使用文件通道进行文件复制
	public void copyUseChannelTransfer() throws IOException {
		try (FileChannel src = FileChannel.open(Paths.get("srcFile.txt"),
				StandardOpenOption.READ)) {
			FileChannel dest = FileChannel.open(Paths.get("descFile.txt"),
					StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			src.transferTo(0, src.size(), dest);
		}
	}

	// 内存映射文件
	public void mapFile() throws IOException {
		try (FileChannel channel = FileChannel.open(Paths.get("channel.txt"),
				StandardOpenOption.READ, StandardOpenOption.WRITE)) {
			MappedByteBuffer buffer = channel
					.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
			byte b = buffer.get(1024 * 1024);
			buffer.put(5 * 1024 * 1024, b);
			buffer.force();
		}
	}

}
