package com.module.user.service.impl;

import com.module.user.dao.TestMapper;
import com.module.user.model.UserInfo;
import com.module.user.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    public void addUser(UserInfo userInfo) {
        this.testMapper.insert(userInfo);
    }

    public UserInfo getUserInfo(int id) {
       return this.testMapper.getUserInfo(Integer.valueOf(id));
    }
}
