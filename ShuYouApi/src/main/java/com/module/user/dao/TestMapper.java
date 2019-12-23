package com.module.user.dao;

import com.module.user.model.UserInfo;

public interface TestMapper {

    int insert(UserInfo paramUserInfo);

    UserInfo getUserInfo(Integer paramInteger);
}
