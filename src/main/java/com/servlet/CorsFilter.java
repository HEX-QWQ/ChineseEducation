package com.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*"); // 设置允许的来源，这里设置为允许所有来源，可根据需求进行配置
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 设置允许的请求方法
        httpResponse.setHeader("Access-Control-Allow-Headers", "*"); // 设置允许的请求头，这里设置为允许所有请求头，可根据需求进行配置

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    // 其他方法（init、destroy）略...
}
