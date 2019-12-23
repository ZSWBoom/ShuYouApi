package com.module.user.shuyou.service.impl;

import com.module.user.shuyou.dataModel.UserInfo;
import com.module.user.shuyou.mapper.UserMapper;
import com.module.user.shuyou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public UserInfo selectByEmail(String email) {
        return this.userMapper.selectByEmail(email);
    }

    public void register(com.module.user.shuyou.dataModel.UserInfo paramUserInfo) {
        this.userMapper.register(paramUserInfo);
    }

}
