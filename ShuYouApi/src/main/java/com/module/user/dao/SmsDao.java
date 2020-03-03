package com.module.user.dao;

import com.module.user.shuyou.dataModel.VerifyCodeModel;

public interface SmsDao {
    boolean putVerifyCode(VerifyCodeModel paramVerifyCodeModel);

    String getVerifyCode(String paramString);
}
