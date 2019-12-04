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
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

/**
 * @ClassName: GetMethodTest
 * @Description:
 * @author 000807
 * @date 2019年12月3日 上午11:36:21
 *
 */

public class PostMethodTest {

	public static void main(String[] args) {
		HttpClient client = new HttpClient();
		client.setConnectionTimeout(30000);
		HttpClientParams params = new HttpClientParams();
		params.setSoTimeout(30000);
		params.setParameter(HttpClientParams.HTTP_CONTENT_CHARSET, "UTF-8");
		client.setParams(params);

		PostMethod postMethod = new PostMethod("http://www.baidu.com");
		postMethod.setParameter("", "");
		try {
			client.executeMethod(postMethod);

			System.out.println(postMethod.getStatusCode());
			System.out.println(postMethod.getStatusText());
			System.out.println(postMethod.getStatusLine());
			Header[] responseHeaders = postMethod.getResponseHeaders();
			for (Header h : responseHeaders) {
				System.out.println(h.getName() + "-" + h.getValue());
			}
			System.out.println(postMethod.getResponseBodyAsString());
			System.out.println(postMethod.getProxyAuthState());
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
