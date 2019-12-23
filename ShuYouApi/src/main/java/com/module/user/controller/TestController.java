package com.module.user.controller;

import com.module.user.domain.BaseResp;
import com.module.user.domain.GetGoodsListByKeywordReq;
import com.module.user.domain.RegisterReq;
import com.module.user.model.GoodsInfo;
import com.module.user.model.UserInfo;
import com.module.user.service.GoodsService;
import com.module.user.service.TestService;
import com.module.user.utils.Md5Utils;
import com.module.user.utils.YuanFenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(produces = {"application/json;charset=UTF-8"}, value = {"/test"})
public class TestController extends BaseController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = {"/addUser"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<UserInfo> getGoodsListByKeyword(@RequestBody RegisterReq req) {

        String mobile = req.getMobile();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserMobile(mobile);
        userInfo.setUserName(mobile);
        userInfo.setUserPwd(Md5Utils.StringToMD5(req.getPwd()));
        this.testService.addUser(userInfo);

        BaseResp resp = new BaseResp();
        resp.setStatus(0);

        return resp;
    }

    @RequestMapping(value = {"/getData"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public BaseResp<UserInfo> getData() {

        UserInfo userInfo = this.testService.getUserInfo(1);
        BaseResp resp = new BaseResp();
        resp.setStatus(0);
        resp.setData(userInfo);

        return resp;
    }
}
