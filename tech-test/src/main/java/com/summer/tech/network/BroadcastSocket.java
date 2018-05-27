package com.summer.tech.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class BroadcastSocket implements Runnable {
	// 多点广播地址224.0.0.1~239.255.255.255
	private static final String BROADCAST_IP = "23O.0.0.1";
	private static final int BROADCAST_PORT = 3000;
	private static final int DATA_LEN = 4096;
	private MulticastSocket socket = null;
	private InetAddress broadcastAddress = null;

	// 用来接收键盘输入
	private Scanner scan = null;
	// 用来接收网络字节
	byte[] inBuff = new byte[DATA_LEN];
	// 用将inBuff封装进DatagramPacket
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	// 封装发送数据的DatagramPacket
	private DatagramPacket outPacket = null;

	public void init() throws IOException {

		try {
			scan = new Scanner(System.in);
			socket = new MulticastSocket(BROADCAST_PORT);
			broadcastAddress = InetAddress.getByName(BROADCAST_IP);
			socket.joinGroup(broadcastAddress);
			// 设置是否阻止MulticastSocket会把消息发给自身，true表示阻止
			socket.setLoopbackMode(false);
			outPacket = new DatagramPacket(new byte[0], 0, broadcastAddress,
					BROADCAST_PORT);
			new Thread(this).start();
			// 读取键盘输入
			while (scan.hasNext()) {
				// 需要先将键盘输入转化为byte字节才能设置DatagramPacket的值
				byte[] buff = scan.nextLine().getBytes();
				outPacket.setData(buff);
				socket.send(outPacket);
			}
		} finally {
			socket.close();
		}
	}

	public static void main(String[] args) throws IOException {
		new BroadcastSocket().init();
	}

	@Override
	public void run() {
		try {
			while (true) {
				socket.receive(inPacket);
				System.out.println("聊天消息："
						+ new String(inBuff, 0, inPacket.getLength()));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			try {
				if (socket != null) {
					socket.leaveGroup(broadcastAddress);
					socket.close();
				}
				System.exit(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}