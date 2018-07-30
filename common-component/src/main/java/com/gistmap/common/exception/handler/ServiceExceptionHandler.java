package com.gistmap.common.exception.handler;

import com.gistmap.common.exception.BaseException;
import com.gistmap.common.response.ResponseData;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServiceExceptionHandler implements HandlerExceptionResolver {


    public ServiceExceptionHandler() {
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof BaseException) {
            response.setStatus(200);
            return JsonModelAndViewBuilder.build(ResponseData.fail((BaseException)ex));
        } else {
            return null;
        }
    }
}
