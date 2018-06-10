package com.summer.tech.network.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class HttpRestTest {

	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		String urlNameString = url + "?" + param;
		try {
			URL resultUrl = new URL(urlNameString);
			URLConnection connection = resultUrl.openConnection();
			// 设置请求头信息
			connection.setRequestProperty("accept", "*/");
			connection.setRequestProperty("connection", "KeepAlive");
			connection.connect();
			// 获取响应头
			Map<String, List<String>> map = connection.getHeaderFields();
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			Charset cs = Charset.forName("UTF-8");
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		String urlNameString = url + "?" + param;
		try {
			URL resultUrl = new URL(urlNameString);
			URLConnection conn = resultUrl.openConnection();
			// 设置请求属性
			conn.setRequestProperty("accept", "*");
			conn.setRequestProperty("connection", "Keep-Alive");
			// 发送post请求必须设置下面两行
			conn.setDoInput(true);
			conn.setDoOutput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.flush();
			Charset cs = Charset.forName("UTF-8");
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		String get = HttpRestTest.sendGet("http://www.baidu.com/", "");
		System.out.println(get);
		System.out.println("===================================");
		String post = HttpRestTest.sendPost("http://www.baidu.com", "");
		System.out.println(post);
	}
}
