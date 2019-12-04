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

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

/**
 * @ClassName: GetMethodTest
 * @Description:
 * @author 000807
 * @date 2019年12月3日 上午11:36:21
 *
 */

public class GetMethodTest {

	public static void main(String[] args) {
		HttpClient client = new HttpClient();
		client.setConnectionTimeout(30000);
		HttpClientParams params = new HttpClientParams();
		params.setSoTimeout(30000);
		params.setParameter(HttpClientParams.HTTP_CONTENT_CHARSET, "UTF-8");
		client.setParams(params);

		GetMethod getMethod = new GetMethod("http://www.baidu.com");
		try {
			client.executeMethod(getMethod);

			System.out.println(getMethod.getStatusCode());
			System.out.println(getMethod.getStatusText());
			System.out.println(getMethod.getStatusLine());
			Header[] responseHeaders = getMethod.getResponseHeaders();
			for (Header h : responseHeaders) {
				System.out.println(h.getName() + "-" + h.getValue());
			}
			System.out.println(getMethod.getResponseBodyAsString());
			System.out.println(getMethod.getProxyAuthState());
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
