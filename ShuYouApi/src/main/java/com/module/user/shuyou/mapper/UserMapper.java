package com.module.user.shuyou.mapper;
import com.module.user.shuyou.dataModel.UserInfo;

public interface UserMapper {

    int register(UserInfo paramUserInfo);

    UserInfo selectByEmail(String email);

}