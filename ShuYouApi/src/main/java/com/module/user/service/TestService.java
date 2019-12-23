package com.module.user.service;

import com.module.user.model.UserInfo;

public interface TestService {

    void addUser(UserInfo userInfo);

    UserInfo getUserInfo(int id);
}
