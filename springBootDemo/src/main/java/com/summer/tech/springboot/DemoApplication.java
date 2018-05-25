package com.summer.tech.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.summer.tech.springboot.servlet.MyServlet;

@SpringBootApplication
@ServletComponentScan
public class DemoApplication {

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new MyServlet(), "/sw/*");// ServletName默认值为首字母小写，即myServlet
	}

	/**
     * 修改DispatcherServlet默认配置 默认是 /
     */
	/*@Bean
	public ServletRegistrationBean dispatcherRegistration(
			DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(
				dispatcherServlet);
		registration.getUrlMappings().clear();
		registration.addUrlMappings("*.do");
		registration.addUrlMappings("*.htm");
		return registration;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
