package com.gistmap.common.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gistmap.common.json.JsonConverter;
import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import okio.Okio;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public final class GateInterceptor implements HandlerInterceptor {
    private static final String requestStartTimeAttributeKey = "request_start";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Long start = System.currentTimeMillis();
        request.setAttribute(requestStartTimeAttributeKey, start);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long end = System.currentTimeMillis();
        Long start = (Long) request.getAttribute(requestStartTimeAttributeKey);

        String method = request.getMethod();
        String URI = request.getServletPath();

        Long duration = end - start;

        LogBuilder node = LogBuilder.builder();

        node.put("url", method + URI);
        node.put("duration", duration);
        node.put("status", response.getStatus());
        node.put("request-id", request.getHeader("request-id"));
        node.put("query", request.getQueryString());
        String body = Okio.buffer(Okio.source(request.getInputStream())).readString(Charsets.UTF_8);
        if (StringUtils.isNotBlank(body)) {
            if (body.startsWith("{")) {
                node.put("body", JsonConverter.toJsonNode(body));
            }
        }

        log.info(node.build());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    private static class LogBuilder {

        private static final JsonNodeFactory factory = JsonNodeFactory.instance;
        private ObjectNode node;

        LogBuilder() {
            node = factory.objectNode();
        }

        static LogBuilder builder() {
            return new LogBuilder();
        }

        String build() {
            return node.toString();
        }

        LogBuilder put(String key, Object value) {
            if (value == null || StringUtils.isBlank(value.toString())) {
                return this;
            }
            if (value instanceof JsonNode) {
                node.set(key, (JsonNode) value);
            } else {
                if (StringUtils.isNotBlank(value.toString())) {
                    node.put(key, value.toString());
                }
            }
            return this;
        }
    }
}
