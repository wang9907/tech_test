package com.summer.tech.springboot;

import com.summer.tech.springboot.importconfig.MyImportSelector;
import com.summer.tech.springboot.servlet.MyFilter;
import com.summer.tech.springboot.servlet.MyServlet;
import com.summer.tech.springboot.servlet.MyServletContextListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.ArrayList;
import java.util.List;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication
@ServletComponentScan
@Import({MyImportSelector.class})
public class DemoApplication {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        //new ServletRegistrationBean(new MyServlet(), "/sw/*");// ServletName默认值为首字母小写，即myServlet
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new MyServlet());
        registrationBean.setLoadOnStartup(1);
        List<String> urlmappings = new ArrayList<>();
        urlmappings.add("/sw/*");
        registrationBean.setUrlMappings(urlmappings);
        registrationBean.setName("myServlet");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.addUrlPatterns("/sw/*");
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean listenerRegistrationBean() {
        ServletListenerRegistrationBean listenerRegistrationBean = new ServletListenerRegistrationBean();
        listenerRegistrationBean.setListener(new MyServletContextListener());
        return listenerRegistrationBean;
    }

    /**
     * 修改DispatcherServlet默认配置 默认是 /
     */
    @Bean
    public ServletRegistrationBean dispatcherRegistration(
            DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean
                registration = new ServletRegistrationBean(dispatcherServlet);
        registration.getUrlMappings().clear();
        registration.addUrlMappings("*.do");
        registration.addUrlMappings("*.htm");
        return registration;
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        //SpringApplication springApplication = new SpringApplication(DemoApplication.class);
    }
}
