package com.gistmap.ddbeat.gateway.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author zhangran
 * @date 2018/7/1
 */

@RestController
//@RefreshScope
public class Controller {
//    @Value("${white.list}")
    public Set<String> value;

    @GetMapping
    public String hello(){
        return value.toString();
    }
}
