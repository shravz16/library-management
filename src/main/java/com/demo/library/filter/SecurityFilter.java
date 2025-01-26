package com.demo.library.filter;

import jakarta.servlet.*;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

public class SecurityFilter implements  Filter {



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, jakarta.servlet.FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
        // Cleanup logic if needed
        System.out.println("Custom Filter Destroyed");
    }
}
