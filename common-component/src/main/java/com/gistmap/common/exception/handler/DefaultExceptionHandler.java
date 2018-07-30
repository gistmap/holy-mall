package com.gistmap.common.exception.handler;

import com.gistmap.common.exception.Exceptions;
import com.gistmap.common.response.ResponseData;
import com.google.common.base.Charsets;
import okio.Okio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class DefaultExceptionHandler implements HandlerExceptionResolver {
    private static final Logger EX = LoggerFactory.getLogger("EX");

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof MissingServletRequestParameterException) {
            response.setStatus(400);
            MissingServletRequestParameterException msrpe = (MissingServletRequestParameterException) ex;
            return JsonModelAndViewBuilder.build(ResponseData.fail(String.format("[%s]字段不能为空", msrpe.getParameterName())));
        } else {
            response.setStatus(500);
            StringBuilder sb = new StringBuilder();

            sb.append("\n\t");
            sb.append(getHeaderParam(request));
            sb.append("\n\t");
            sb.append(request.getQueryString());
            sb.append("\n\t");
            try {
                String body = Okio.buffer(Okio.source(request.getInputStream())).readString(Charsets.UTF_8).trim();
                if (body.length() > 0) {
                    sb.append(body);
                    sb.append("\n\t");
                }
            } catch (IOException e) {
                //ignore
            }
            sb.append(Exceptions.getStackTraceAsString(ex));
            EX.error(sb.toString());
            return JsonModelAndViewBuilder.build(ResponseData.fail("内部错误"));
        }
    }

    private String getHeaderParam(HttpServletRequest request) {
        String method = request.getMethod();
        String URI = request.getServletPath();
        StringBuilder sb = new StringBuilder(512);
        sb.append(method);
        sb.append(URI);
        return sb.toString();
    }
}
