package com.summer.tech.network.tcpchat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static final int SERVER_PORT = 33000;
	public static ChatMap<String, PrintStream> clients = new ChatMap();

	public void init() {
		ServerSocket ss;
		try {
			ss = new ServerSocket(SERVER_PORT);
			while (true) {
				Socket socket = ss.accept();// 从连接请求队列获取连接
				new ServerThread(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
