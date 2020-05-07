/**
  * @Title: TestController.java
  * @Package com.gk.platform.cliagent.web.controller
  * @Description:
  * @author 000807
  * @date 2019年10月22日
  * @version V1.0
  */

package com.summer.tech.springboot.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description:
 * @author 000807
 * @date 2019年10月22日 下午2:42:05
 *
 */

@RestController
public class TestController {

	@RequestMapping("/test")
	public String test(HttpServletRequest request, HttpServletResponse response) {
		try {
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String name = headerNames.nextElement();
				System.out.println(name + ":" + request.getHeader(name));
			}
			System.out.println("请求头结束");
			BufferedReader reader = request.getReader();
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "success";
	}
}
