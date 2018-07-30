package com.gistmap.ddbeat.gateway.filter;

import com.gistmap.ddbeat.gateway.comm.ResultEnum;
import com.gistmap.ddbeat.gateway.util.ResponseUtil;
import com.gistmap.ddbeat.gateway.util.WhiteListPool;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author zhangran
 */
@Slf4j
public class SimpleFilter extends ZuulFilter {

    @Autowired
    private WhiteListPool pool;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        String accessToken = request.getHeader(ResponseUtil.ACCESS_TOKEN);
        if (isWhiteList(accessToken)) {
            ResponseUtil.deny(ctx, ResultEnum.SIGNATURE_ERROR);
        } else {
            ResponseUtil.routing(ctx);
        }
        return null;
    }

    public  boolean isWhiteList(String url){
        return pool.getValue().stream().anyMatch(url::contains);
    }


}
