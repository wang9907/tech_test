/**
  * @Title: GetMethodTest.java
  * @Package com.summer.tech.network.httpclient.version3
  * @Description:
  * @author 000807
  * @date 2019年12月3日
  * @version V1.0
  */

package com.summer.tech.network.httpclient.version3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @ClassName: GetMethodTest
 * @Description:
 * @author 000807
 * @date 2019年12月3日 上午11:36:21
 *
 */

public class PostMethodTest {

	public static void main(String[] args) {
		try {
			CloseableHttpClient httpClient = HttpClients.custom().build();
			// GET请求
			HttpGet httpGet = new HttpGet("http://www.baidu.com");
			CloseableHttpResponse response = httpClient.execute(httpGet);
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(response.getStatusLine().getReasonPhrase());
			Header[] responseHeaders = response.getAllHeaders();
			for (Header h : responseHeaders) {
				System.out.println(h.getName() + "-" + h.getValue());
			}

			/*
			 * HttpEntity entity = response.getEntity(); BufferedReader reader =
			 * new BufferedReader(new InputStreamReader(entity.getContent()));
			 * String line = null; while ((line = reader.readLine()) != null) {
			 * System.out.println(line); }
			 */
			System.out.println(EntityUtils.toString(response.getEntity()));

			// POST请求
			HttpPost httpPost = new HttpPost("http://www.baidu.com");
			List<NameValuePair> formParams = new ArrayList<>();
			// 表单参数
			formParams.add(new BasicNameValuePair("name1", "value1"));
			formParams.add(new BasicNameValuePair("name2", "value2"));

			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httpPost.setEntity(entity);

			CloseableHttpResponse resp = httpClient.execute(httpPost);
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
