package com.cmcc.spring.config;

import com.cmcc.spring.interceptor.AccessLimitInterceptor;
import com.cmcc.spring.interceptor.RequestContextInterceptor;
import com.cmcc.spring.interceptor.RequestLogMdcInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    private AccessLimitInterceptor accessLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestLogMdcInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new RequestContextInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(accessLimitInterceptor).addPathPatterns("/**");
    }

}
