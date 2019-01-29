package com.cmcc.controller;

import com.cmcc.pojo.User;
import com.cmcc.service.testService;
import com.cmcc.spring.Annotation.AccessLimit;
import com.cmcc.spring.RequestContext;
import com.cmcc.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chef")
@Slf4j
public class UserAdmin {


    @Autowired
    private testService admin;

    @Value("${foo}")
    String foo;

    @GetMapping("test")
    @AccessLimit(limit = 3, sec = 60)
    public ApiResponse TEST() {
        System.out.println(RequestContext.getRequest());
        User test = admin.test(1);
        log.info("foo=" + foo);
        return ApiResponse.build().success(test);
    }

    @GetMapping("test1")
    public ApiResponse TEST1() {
        User test = admin.test(1);
        return ApiResponse.build().success(test);
    }
}
