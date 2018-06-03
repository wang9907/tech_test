package com.summer.tech.network.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;

import javax.swing.JOptionPane;

public class ChatUtil {

	// 广播地址
	private static final String BROADCAST_IP = "230.0.0.1";
	// 广播端口
	public static final int BROADCAST_PORT = 3000;
	// 数据报大小
	private static final int DATA_LEN = 4096;
	// 广播socket
	private MulticastSocket socket = null;
	// 私聊socket
	private DatagramSocket singleSocket = null;
	// 广播IP地址
	private InetAddress broadcastAddress = null;
	// 接收网络数据字节数组
	byte[] inBuff = new byte[DATA_LEN];
	// 接收数据包
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	// 发送数据包
	private DatagramPacket outPacket = null;
	private LanChat lanChat;

	public ChatUtil(LanChat lanChat) throws IOException, InterruptedException {
		this.lanChat = lanChat;
		// 创建广播socket、
		socket = new MulticastSocket(BROADCAST_PORT);
		// 创建数据包socket
		singleSocket = new DatagramSocket(BROADCAST_PORT + 1);
		broadcastAddress = InetAddress.getByName(BROADCAST_IP);
		// 将socket加入广播地址
		socket.joinGroup(broadcastAddress);
		// 设置MulticastSocket发送的的数据报被回送到自身
		socket.setLoopbackMode(false);
		// 初始化发送数据包
		outPacket = new DatagramPacket(new byte[0], 0, broadcastAddress,
				BROADCAST_PORT);
		// 启动两个读取网络数据的线程
		new ReadBroad().start();
		Thread.sleep(1);
		new ReadSingle().start();
	}

	// 　广播消息的工具方法
	public void broadCast(String msg) {
		// 将msg字符串转换为字节数组
		byte[] buff = msg.getBytes();
		// 把内容存放到发送数据包
		outPacket.setData(buff);
		try {
			socket.send(outPacket);
		} catch (IOException e) {
			e.printStackTrace();
			if (socket != null) {
				socket.close();
			}
			JOptionPane.showMessageDialog(null, "发送消息异常，请确认30000端口空闲，且网络连接正常！",
					"网络异常", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	public void sendSingle(String msg, SocketAddress dest) {
		byte[] buff = msg.getBytes();
		DatagramPacket packet = new DatagramPacket(buff, buff.length, dest);
		try {
			singleSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
			if (singleSocket != null) {
				singleSocket.close();
			}
			JOptionPane.showMessageDialog(null, "发送信息异常，请确认30001端口空闲，且网络连接正常！",
					"网络异常", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	class ReadBroad extends Thread {
		public void run() {
			while (true) {
				try {
					socket.receive(inPacket);
					String msg = new String(inBuff, 0, inPacket.getLength());
					System.out.println(msg);
				} catch (IOException e) {
					e.printStackTrace();
					if (socket != null) {
						socket.close();
					}
					JOptionPane.showMessageDialog(null,
							"接收信息异常，请确认30000端口空闲，且网络连接正常！", "网络异常",
							JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}

			}
		}
	}
	
	class ReadSingle extends Thread{
		public void run(){
			try {
				singleSocket.receive(inPacket);
				String msg = new String(inBuff, 0, inPacket.getLength());
				System.out.println(msg);
			} catch (IOException e) {
				e.printStackTrace();
				if (singleSocket != null) {
					singleSocket.close();
				}
				JOptionPane.showMessageDialog(null,
						"接收信息异常，请确认30000端口空闲，且网络连接正常！", "网络异常",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
			
		}
	}
	
}
