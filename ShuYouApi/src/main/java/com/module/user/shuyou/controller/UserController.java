package com.module.user.shuyou.controller;

import com.module.user.shuyou.dataModel.UserInfo;
import com.module.user.shuyou.domain.BaseResp;
import com.module.user.shuyou.domain.LoginReq;
import com.module.user.shuyou.domain.RegisterReq;
import com.module.user.shuyou.service.UserService;
import com.module.user.shuyou.utils.Md5Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(produces = {"application/json;charset=UTF-8"}, value = {"/user"})
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/register"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp register(@RequestBody RegisterReq req) {
        BaseResp resp = new BaseResp();
        String email = req.getEmail();
        if(TextUtils.isEmpty(email)){
            resp.setStatus(-1);
            resp.setMessage("请输入账号");
            return resp;
        }
        UserInfo userInfo = this.userService.selectByEmail(email);
        if (userInfo != null) {
            resp.setStatus(-1);
            resp.setMessage("账号已被注册");
            return resp;
        }

        if(!email.contains("@myhexin.com")) {
            resp.setStatus(-1);
            resp.setMessage("请输入正确格式账号");
            return resp;
        }

        String userName = email.replace("@myhexin.com", "");
        String password = req.getPassword();
        String phone = req.getPhoneNum();

        userInfo = new UserInfo();
        userInfo.setPhoneNum(phone);
        userInfo.setUserName(userName);
        userInfo.setEmail(email);
        userInfo.setPassword(Md5Utils.StringToMD5(password));
        this.userService.register(userInfo);
        resp.setStatus(0);
        resp.setMessage("注册成功");
        return resp;
    }

    @RequestMapping(value = {"/login"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<UserInfo> loginByPwd(@RequestBody LoginReq req) {
        BaseResp resp = new BaseResp();

        String email = req.getEmail();
        String pwd = req.getPassword();

        if (StringUtils.isEmpty(email)) {
            resp.setStatus(-1);
            resp.setMessage("账号为空");
            return resp;
        }

        if (StringUtils.isEmpty(pwd)) {
            resp.setStatus(-1);
            resp.setMessage("密码为空");
            return resp;
        }

        com.module.user.shuyou.dataModel.UserInfo userInfo = this.userService.selectByEmail(email);
        if (userInfo == null) {
            resp.setStatus(-1);
            resp.setMessage("用户不存在");
            return resp;
        }

        if (!Md5Utils.StringToMD5(pwd).equals(userInfo.getPassword())) {
            resp.setStatus(-1);
            resp.setMessage("密码错误");
            return resp;
        }

        userInfo.setPassword(null);
        resp.setStatus(0);
        resp.setMessage("登录成功");
        resp.setData(userInfo);
        return resp;
    }

}
