package com.gistmap.ddbeat.gateway.config;

import com.gistmap.ddbeat.gateway.filter.SimpleFilter;
import org.springframework.cloud.netflix.zuul.filters.discovery.ServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangran
 * @date 2018/6/6
 */
@Configuration
public class FilterConfig {

    private final static String CONSUL = "consul";
    @Bean
    public SimpleFilter signatureFilter(){
        return new SimpleFilter();
    }

}
