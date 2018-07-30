package com.gistmap.ddbeat.gateway.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author zhangran
 * @date 2018/7/1
 */
@Component
@RefreshScope
public class WhiteListPool {

    @Value("${white.list}")
    public Set<String> value;

    public Set<String> getValue() {
        System.out.println(value.toString());
        return value;
    }
}
