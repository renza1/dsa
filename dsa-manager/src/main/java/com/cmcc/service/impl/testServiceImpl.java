package com.cmcc.service.impl;

import com.cmcc.mapper.UserMapper;
import com.cmcc.pojo.User;
import com.cmcc.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class testServiceImpl implements testService {



    @Autowired
    private UserMapper userMapper;

    @Override
    public User test(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
