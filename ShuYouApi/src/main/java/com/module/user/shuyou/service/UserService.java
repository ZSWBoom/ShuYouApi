package com.module.user.shuyou.service;


import com.module.user.shuyou.dataModel.UserInfo;

public interface UserService {

    UserInfo selectByEmail(String paramString);

    void register(UserInfo paramUserInfo);
}
