package com.summer.tech.javase7.io;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.util.Enumeration;

public class TimeClient {

	public static void main(String[] args) throws IOException {
		 new TimeClient().start();
		//showNetworkInterface();
	}

	public void start() throws IOException {
		NetworkInterface ni = NetworkInterface.getByName("wlan0");
		int port = 5000;
		try (DatagramChannel dc = DatagramChannel
				.open(StandardProtocolFamily.INET)
				.setOption(StandardSocketOptions.SO_REUSEADDR, true)
				.bind(new InetSocketAddress(port))
				.setOption(StandardSocketOptions.IP_MULTICAST_IF, ni)) {
			InetAddress group = InetAddress.getByName("224.0.0.2");
			MembershipKey key = dc.join(group, ni);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			dc.receive(buffer);
			buffer.flip();
			byte[] data = new byte[buffer.limit()];
			buffer.get(data);
			String str = new String(data);
			System.out.println(str);
			key.drop();
		}
	}

	public static void showNetworkInterface() throws SocketException {
		Enumeration<NetworkInterface> enu = NetworkInterface
				.getNetworkInterfaces();
		while (enu.hasMoreElements()) {
			NetworkInterface in = enu.nextElement();
			// 获得与该网络接口绑定的 IP 地址，一般只有一个
			Enumeration<InetAddress> addresses = in.getInetAddresses();
			while (addresses.hasMoreElements()) {
				InetAddress addr = addresses.nextElement();
				if (addr instanceof Inet4Address) { // 只关心 IPv4 地址
					System.out.println("网卡接口名称：" + in.getName());
					System.out.println("网卡接口地址：" + addr.getHostAddress());
					System.out.println();
				}
			}
		}
	}
}
