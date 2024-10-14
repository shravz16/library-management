package com.demo.library.config;

import com.demo.library.filter.SecurityFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class SecurityConfig {

    @Bean
    public FilterRegistrationBean<SecurityFilter> loggingFilter() {
        FilterRegistrationBean<SecurityFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new SecurityFilter());
        registrationBean.addUrlPatterns("/*"); // Apply filter to all URL patterns

        return registrationBean;
    }


}
