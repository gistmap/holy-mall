package com.gistmap.order.configuration;

import com.gistmap.common.exception.handler.DefaultExceptionHandler;
import com.gistmap.common.exception.handler.PropertyNamingStrategyArgumentResolver;
import com.gistmap.common.exception.handler.ServiceExceptionHandler;
import com.gistmap.common.filter.GateInterceptor;
import com.gistmap.common.filter.RequestWrapperFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author zhangran
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new ServiceExceptionHandler());
        exceptionResolvers.add(new DefaultExceptionHandler());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PropertyNamingStrategyArgumentResolver());
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GateInterceptor());
    }

    @Bean
    public FilterRegistrationBean requestWrapperFilter() {
        FilterRegistrationBean<RequestWrapperFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RequestWrapperFilter());
        registration.setDispatcherTypes(javax.servlet.DispatcherType.REQUEST);
        return registration;
    }
}
