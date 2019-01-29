package com.cmcc.spring.interceptor;

import com.cmcc.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestLogMdcInterceptor extends HandlerInterceptorAdapter {
    public static final Logger logger = LoggerFactory.getLogger(RequestLogMdcInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String unqId = RandomStringUtils.randomNumeric(16);
        MDC.put("req.id", unqId);
        MDC.put("req.uri", request.getRequestURI());
        MDC.put("req.beginTime",System.currentTimeMillis() + "");

        logger.info("req id : {}, req uri : {},  reqmethod : {}, param : {}",
                unqId, request.getRequestURI(), request.getMethod(),
                JacksonUtil.toJson(request.getParameterMap()));

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        logger.info("req id : {}, 请求：{}, 耗时：{} ms",
                MDC.get("req.id"), MDC.get("req.uri"), System.currentTimeMillis() - Long.parseLong(MDC.get("req.beginTime")));

        MDC.remove("req.id");
        MDC.remove("req.uri");
        MDC.remove("req.beginTime");

        super.afterCompletion(request, response, handler, ex);
    }
}