package com.summer.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.summer.springboot.servlet.MyServlet;

@SpringBootApplication
public class DemoApplication {

	@Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new MyServlet(), "/sw/*");// ServletName默认值为首字母小写，即myServlet
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
