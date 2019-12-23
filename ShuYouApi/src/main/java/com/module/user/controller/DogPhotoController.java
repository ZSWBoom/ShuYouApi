package com.module.user.controller;

import com.module.user.domain.BaseResp;
import com.module.user.domain.GetDogIntroduceReq;
import com.module.user.service.DogDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(produces = {"application/json;charset=UTF-8"}, value = {"/dog"})
public class DogPhotoController extends BaseController{
    @Autowired
    private DogDetailService dogDetailService;

    @RequestMapping(value = {"/upload"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<String> upLoad(@RequestBody GetDogIntroduceReq req) {
        BaseResp resp = new BaseResp();
        String name = req.getName();

        String str = dogDetailService.getDogDetail(name);

        resp.setStatus(0);
        resp.setMessage("数据获取成功");
        resp.setData(name);
        return resp;
    }
}
