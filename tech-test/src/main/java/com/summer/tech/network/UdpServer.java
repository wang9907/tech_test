package com.summer.tech.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {

	public UdpServer() {

	}

	private static final int PORT = 30000;
	// 定义每个数据报最大大小为4k
	private static final int DATA_LEN = 4096;
	// 定义接收网络数据的字节数组
	byte[] inBuff = new byte[DATA_LEN];
	// 以指定字节数组创建准备接收的DatagramSocket对象
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	// 定义一个用于发送的DatagramPacket对象
	private DatagramPacket outPacket;
	// 定义一个字符串数组，服务端发送该数组的元素
	String[] books = new String[] { "疯狂Java讲义", "轻量级Java EE企业应用实战",
			"疯狂Android讲义", "疯狂Ajax讲义" };

	public void init() throws IOException {
		try (DatagramSocket socket = new DatagramSocket(PORT)) {
			for (int i = 0; i < 1000; i++) {
				// 读取socket中的数据，读到的数据放入inPacket封装的数组里
				socket.receive(inPacket);
				// 判断inPacket.getData()和inBuff是否是同一个数组
				System.out.println(inBuff == inPacket.getData());
				// 将接收的内容转换成字符串后输出
				System.out.println(new String(inBuff, 0, inPacket.getLength()));
				// 从字符串数组取出一个元素作为发送数据
				byte[] sendData = books[i % 4].getBytes();
				// 以指定的字节数组作为发送数据，以刚收到的DatagramPacket的源SocketAddress作为目标SocketAddress创建DatagramPacket
				outPacket = new DatagramPacket(sendData, sendData.length,inPacket.getSocketAddress()); 
				socket.send(outPacket);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new UdpServer().init();
	}
}
