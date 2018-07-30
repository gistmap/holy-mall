package com.gistmap.common.filter;


import javax.servlet.*;
import java.io.IOException;


public class RequestWrapperFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MultiReadHttpServletRequest wrapped = new MultiReadHttpServletRequest(servletRequest);
        filterChain.doFilter(wrapped, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
