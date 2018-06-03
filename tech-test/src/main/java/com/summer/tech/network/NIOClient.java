package com.summer.tech.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class NIOClient {

	private Selector selector = null;
	private static final int PORT = 3001;
	private Charset charset = Charset.forName("UTF-8");
	private SocketChannel sc = null;

	public void init() throws IOException {
		selector = Selector.open();
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
		// 调用open静态方法创建连接到指定主机的SocketChannel
		sc = SocketChannel.open(isa);
		sc.configureBlocking(false);
		sc.register(selector, SelectionKey.OP_READ);
		// 创建子线程读取服务器返回的数据
		new ClientThread().start();
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			sc.write(charset.encode(line));
		}
		scan.close();
	}

	private class ClientThread extends Thread {
		public void run(){
			try{
				//有事件产生
				while(selector.select()>0){
					//被选中的key集合selectedKeys表示需要进行IO处理的channel集合
					for(SelectionKey sk:selector.selectedKeys()){
						selector.selectedKeys().remove(sk);
						if(sk.isReadable()){
							SocketChannel sc = (SocketChannel)sk.channel();
							ByteBuffer buff = ByteBuffer.allocate(1024);
							String content ="";
							while(sc.read(buff)>0){
								buff.flip();
								content += charset.decode(buff);
							}
							System.out.println("聊天消息："+content);
							sk.interestOps(SelectionKey.OP_READ);
						}
					}
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		new NIOClient().init();
	}
}
