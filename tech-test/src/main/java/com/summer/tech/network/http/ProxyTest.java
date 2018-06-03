package com.summer.tech.network.http;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProxyTest {

	final String PROXY_ADDR = "172.20.230.5";
	final int PROXY_PORT = 3128;
	String urlStr = "http://www.baidu.com";

	public void init() throws IOException {
		URL url = new URL(urlStr);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
				PROXY_ADDR, PROXY_PORT));
		// 显式指定代理对象
		// URLConnection conn = url.openConnection(proxy);
		// 指定默认代理对象,实践表明代理连不上的话，就直接不使用代理直接访问
		ProxySelector.setDefault(new ProxySelector() {
			public List<Proxy> select(URI uri) {
				List<Proxy> list = new ArrayList<Proxy>();
				list.add(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
						PROXY_ADDR, PROXY_PORT)));
				return list;
			}

			public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
				System.out.println("代理对象连接失败！");
			}
		});
		URLConnection conn = url.openConnection();
		conn.setConnectTimeout(5000);
		Scanner scan = new Scanner(conn.getInputStream());
		PrintStream ps = new PrintStream("index.html");
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			System.out.println(line);
			ps.println(line);
		}
		ps.close();
		scan.close();
	}

	public static void main(String[] args) throws IOException {
		new ProxyTest().init();
	}
}
