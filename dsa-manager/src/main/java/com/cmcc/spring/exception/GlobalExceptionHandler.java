package com.cmcc.spring.exception;

import com.cmcc.util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = ParamException.class)
    @ResponseBody
    public ApiResponse paramExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
        ParamException e = (ParamException) ex;
        return ApiResponse.build().paramError(e.getMessage());
    }



    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ApiResponse bizExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
        BizException e = (BizException) ex;
        return ApiResponse.build().error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse exceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
        logger.error("server error", ex);
        return ApiResponse.build().error(ex.getMessage());
    }

}
