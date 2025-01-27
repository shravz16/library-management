package com.demo.library.filter;

import com.demo.library.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


import java.io.IOException;
@Component
public class SecurityFilter implements  Filter {

    private final UserService userService;

    @Autowired
    public SecurityFilter(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, jakarta.servlet.FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();

        if (uri.startsWith("/api/") && !uri.equals("/api/auth/login") && !uri.equals("/api/auth/register")) {
            String apiKey = request.getHeader("api-key");

            if (apiKey == null || apiKey.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("API key is missing");
                return;
            }

            if(userService.findByUserKey(apiKey)==null){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid API key");
                return;
            }


        }

        // If we get here, either it's not an API route or the API key was valid
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        // Cleanup logic if needed
        System.out.println("Custom Filter Destroyed");
    }
}
