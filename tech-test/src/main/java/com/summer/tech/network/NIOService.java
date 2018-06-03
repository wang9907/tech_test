package com.summer.tech.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NIOService {
	// 用于检查所有channel状态的seletor
	private Selector selector = null;
	private final int PORT = 3001;
	private Charset charset = Charset.forName("UTF-8");

	public void init() throws Exception {
		selector = Selector.open();
		// 通过open方法打开一个未绑定的ServerSocketChannel
		ServerSocketChannel server = ServerSocketChannel.open();
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
		server.bind(isa);
		server.configureBlocking(false);
		// 注册channel到selector
		server.register(selector, SelectionKey.OP_ACCEPT);
		// 轮询检测有没有事件发生
		while (true) {
			// select()将会一直阻塞，直到选择了至少一个channel（进行通信），此时sector就会调用wakeup(),select()方法才能返回
			int num = selector.select();
			Thread.sleep(1000);
			// select()方法返回后，用selectedKeys()返回对应channel的SelectionKey集合,通过key.channel()方法可以返回对应的channel实例
			// 被选中的key集合selectedKeys表示需要进行IO处理的channel集合,一个key代表一个channel
			System.out.println("size=" + selector.selectedKeys().size());
			for (SelectionKey sk : selector.selectedKeys()) {
				// 从selector上的已选择的key集合中删除正在处理的key
				selector.selectedKeys().remove(sk);
				// 客户端连接请求事件
				if (sk.isAcceptable()) {
					// 调用accept方法接受请求，产生一个服务器端的SocketChannel
					// 在非阻塞模式下，如果没有连接则直接返回null
					SocketChannel sc = server.accept();
					// 设置为非阻塞
					sc.configureBlocking(false);
					// 将SocketChannel注册到selector
					sc.register(selector, SelectionKey.OP_READ);
					// 再将sk对应的channel设置为请求准备接受其他请求
					sk.interestOps(SelectionKey.OP_ACCEPT);
				}
				// 数据读取时间内
				if (sk.isReadable()) {
					// 获取sk对应的channel
					SocketChannel sc = (SocketChannel) sk.channel();
					// channel中的数据必须先写入buffer中，然后才能写入进
					ByteBuffer buff = ByteBuffer.allocate(1024);
					String content = "";

					try {
						while (sc.read(buff) > 0) {
							// buffer 复位
							buff.flip();
							content += charset.decode(buff);
						}
						System.out.println("读取数据为：" + content);
					} catch (IOException ex) {
						// 从已选择key集合中取消sk,下一次select()时此channel将自动被删除
						ex.printStackTrace();
						sk.cancel();
						if (sk.channel() != null) {
							sk.channel().close();
						}
					}
					if (content.length() > 0) {
						for (SelectionKey key : selector.keys()) {
							Channel targetChannel = key.channel();
							if (targetChannel instanceof SocketChannel) {
								SocketChannel dest = (SocketChannel) targetChannel;
								dest.write(charset.encode(content));
							}
						}
					}
				}
			}

		}

	}

	public static void main(String[] args) throws Exception {
		new NIOService().init();
	}
}
