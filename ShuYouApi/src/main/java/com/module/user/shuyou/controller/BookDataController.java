package com.module.user.shuyou.controller;

import com.module.user.controller.BaseController;
import com.module.user.domain.BaseResp;
import com.module.user.domain.GetGoodsListReq;
import com.module.user.model.GoodsInfo;
import com.module.user.utils.YuanFenConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(produces = {"application/json;charset=UTF-8"}, value = {"/bookData"})
public class BookDataController extends BaseController {

    @RequestMapping(value = {"/getBook"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<List<GoodsInfo>> getGoodsList(@RequestBody GetGoodsListReq req) {
        BaseResp resp = new BaseResp();

        List<GoodsInfo> list = this.goodsService.getGoodsList(req.getCategoryId(), req.getPageNo());
        if ((list == null) || (list.size() == 0)) {
            resp.setStatus(0);
            resp.setMessage("列表为空");
            return resp;
        }

        for (GoodsInfo info : list) {
            info.setGoodsDefaultPrice(YuanFenConverter.changeY2F(info.getGoodsDefaultPrice()));
            info.setMaxPage(Integer.valueOf(this.goodsService.getAllGoodsList(req.getCategoryId()).size() / 6 + 1));
        }

        resp.setStatus(0);
        resp.setMessage("列表获取成功");
        resp.setData(list);
        return resp;
    }
}
