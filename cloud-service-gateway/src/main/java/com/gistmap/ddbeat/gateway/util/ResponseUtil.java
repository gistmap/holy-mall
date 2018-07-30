package com.gistmap.ddbeat.gateway.util;

import com.gistmap.ddbeat.gateway.comm.ResponseData;
import com.gistmap.ddbeat.gateway.comm.ResultEnum;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangran
 * @date 2018/6/22
 */
public class ResponseUtil {


    public static final String ACCESS_TOKEN = "access-token";


    public static void deny(RequestContext ctx, ResultEnum error) {
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(400);
        ctx.getResponse().setContentType("application/json; charset=utf-8");
        ctx.setResponseBody(JsonConverter.toJson(ResponseData.of(error)));
        ctx.set("isSuccess", false);
    }

    public static void routing(RequestContext ctx) {
        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        ctx.set("isSuccess", true);
    }


}
