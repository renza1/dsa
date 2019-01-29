package com.cmcc.spring.interceptor;

import com.cmcc.spring.Annotation.AccessLimit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

//创建拦截器类实现HandlerInterceptor 接口
@Slf4j
@Component
public class AccessLimitInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
        //判断handle是否为方法handle
        if (handle instanceof HandlerMethod) {
            //是的话转为HandleMethod
            HandlerMethod handleMethod = (HandlerMethod)handle;
            //获取方法
            Method method = handleMethod.getMethod();
            //判断该方法上是否有自定义注解@AccessLimit
            if (!method.isAnnotationPresent(AccessLimit.class)) {
                //不存在该注解则放行
                return super.preHandle(request, response, handle);
            }
            //获取自定义注解对象
            AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                //放行
                return super.preHandle(request, response, handle);
            }
            //获取注解属性值
            int limit = accessLimit.limit();
            int sec = accessLimit.sec();
            //可将用户的id加上  限制每一个用户只能访问几次
            String key = request.getRequestURI();
            log.info("key : {}",key);
            //从redis中获取记录
            Integer maxLimit = (Integer)redisTemplate.opsForValue().get(key);
            if (maxLimit == null) {
                //第一次，计数器设置为1，设置redis过期时间
                redisTemplate.opsForValue().set(key, 1, sec,TimeUnit.SECONDS);
            } else if (maxLimit < limit) {
                //计数器加1
                redisTemplate.opsForValue().set(key, maxLimit + 1, sec, TimeUnit.SECONDS);
            } else {
                output(response, "请求频繁，稍后再试");
                return false;
            }
        }
        return super.preHandle(request, response, handle);
    }

    public void output(HttpServletResponse response, String str) throws IOException {
        //设置content-type
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            //获得输出流
            outputStream = response.getOutputStream();
            outputStream.write(str.getBytes("utf-8"));
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        }
    }

}
