package com.cmcc.controller;

import com.cmcc.pojo.User;
import com.cmcc.service.testService;
import com.cmcc.spring.Annotation.AccessLimit;
import com.cmcc.spring.RequestContext;
import com.cmcc.util.ApiResponse;
import com.cmcc.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chef")
public class UserAdmin {


    @Autowired
    private  testService admin;

    @GetMapping("test")
    @AccessLimit(limit = 3, sec = 60)
    public ApiResponse TEST(){
        System.out.println(RequestContext.getRequest());
        User test = admin.test(1);
        return ApiResponse.build().success(test);
    }

    @GetMapping("test1")
    public ApiResponse TEST1(){
        User test = admin.test(1);
        return ApiResponse.build().success(test);
    }
}
