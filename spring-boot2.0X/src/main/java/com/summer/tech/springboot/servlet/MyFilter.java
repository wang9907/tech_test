package com.summer.tech.springboot.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest){
            HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            System.out.println(httpServletRequest.getRequestURI());
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("filter destory");
    }
}
