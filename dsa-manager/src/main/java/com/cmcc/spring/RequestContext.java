package com.cmcc.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class RequestContext {

    private static final Logger logger            = LoggerFactory.getLogger(RequestContext.class);
    private static ThreadLocal<HttpServletRequest>  requestContext    = new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<HttpServletResponse> responseContext   = new ThreadLocal<HttpServletResponse>();
    private static ThreadLocal<Object>              controllerContext = new ThreadLocal<Object>();

    public static void init(HttpServletRequest request, HttpServletResponse response, Object controller) {

        setRequest(request);
        setResponse(response);
        setController(controller);
    }

    public static HttpSession getSession() {
        if (getRequest() == null) {
            return null;
        }
        return getRequest().getSession(true);
    }

    public static HttpServletRequest getRequest() {
        return requestContext.get();
    }

    public static HttpServletResponse getResponse() {
        return responseContext.get();
    }

    public static void setRequest(HttpServletRequest request) {
        requestContext.set(request);
    }

    public static void setResponse(HttpServletResponse response) {
        responseContext.set(response);
    }

    public static void setController(Object controller) {
        controllerContext.set(controller);
    }

    public static <T> T getController() {
        return (T) controllerContext.get();
    }

    public static void clear() {
        requestContext.remove();
        responseContext.remove();
        controllerContext.remove();
    }
}
