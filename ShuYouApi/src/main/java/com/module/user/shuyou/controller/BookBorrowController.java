package com.module.user.shuyou.controller;

import com.module.user.shuyou.dataModel.ApplyDataModel;
import com.module.user.shuyou.dataModel.BookModel;
import com.module.user.shuyou.dataModel.BorrowDataModel;
import com.module.user.shuyou.dataModel.PendDataModel;
import com.module.user.shuyou.domain.ApplyReq;
import com.module.user.shuyou.domain.BaseResp;
import com.module.user.shuyou.service.BookBorrowService;
import com.module.user.shuyou.service.BookDataService;
import com.module.user.shuyou.utils.DateUtil;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(produces = {"application/json;charset=UTF-8"}, value = {"/bookOperation"})
public class BookBorrowController extends BaseController {
    @Autowired
    BookBorrowService booksBorrowService;
    @Autowired
    BookDataService booksInfoService;

    private static final int STATUS_BOOK_NORMAL = 0;
    private static final int STATUS_BOOK_BORROW = 1;
    private static final int STATUS_BOOK_REMOVE = 2;

    /**
     * 用户申请借书接口
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/addApplyInfo"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp addApplyInfo(@RequestBody ApplyReq req) {
        BaseResp resp = new BaseResp();
        if (req.getBookId() == null || req.getUserId() == null) {
            resp.setStatus(-1);
            resp.setMessage("参数错误");
            return resp;
        }
        //查看书籍状态是否可借
        BookModel booksInfo = this.booksInfoService.getBookDetail(req.getBookId());
        if (booksInfo != null) {
            if (booksInfo.getHolderId().intValue() == req.getUserId().intValue()) {
                resp.setStatus(-1);
                resp.setMessage("无法借阅本人持有书籍");
                return resp;
            }
            int status = booksInfo.getStatus();
            if (status == STATUS_BOOK_BORROW) {
                resp.setStatus(-1);
                resp.setMessage("该书籍已被借出");
                return resp;
            } else if (status == STATUS_BOOK_REMOVE) {
                resp.setStatus(-1);
                resp.setMessage("该书籍已下架");
                return resp;
            }
        }

        List<ApplyDataModel> currentDataList = this.booksBorrowService.getApplyList(req.getUserId(), req.getBookId());
        if (currentDataList == null || currentDataList.size() == 0) {
            this.booksBorrowService.applyBook(req.getUserId(), req.getBookId());
            resp.setStatus(0);
            resp.setMessage("申请成功");
        } else {
            resp.setStatus(-1);
            resp.setMessage("不允许重复申请");
        }
        return resp;
    }

    /**
     * 用户删除借书申请接口
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/deleteApplyInfo"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp deleteApplyInfo(@RequestBody ApplyReq req) {
        BaseResp resp = new BaseResp();
        if (req.getId() == null) {
            resp.setStatus(-1);
            resp.setMessage("参数错误");
            return resp;
        }
        List<ApplyDataModel> currentDataList = this.booksBorrowService.getApplyListById(req.getId());
        if (currentDataList != null && currentDataList.size() > 0) {
            this.booksBorrowService.cancelBookApplyData(req.getId());
            resp.setStatus(0);
            resp.setMessage("取消申请成功");
        } else {
            resp.setStatus(-1);
            resp.setMessage("操作错误");
        }
        return resp;
    }

    /**
     * 获取当前用户已提交但未审核通过申请列表数据
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/getApplyingInfo"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<List<ApplyDataModel>> getApplyInfo(@RequestBody ApplyReq req) {
        BaseResp resp = new BaseResp();
        if (req.getUserId() == null) {
            resp.setStatus(-1);
            resp.setMessage("参数错误");
            return resp;
        }
        List<ApplyDataModel> applyDataModels = this.booksBorrowService.getApplyingList(req.getUserId());
        if (applyDataModels != null && applyDataModels.size() > 0) {
            selectBookData(applyDataModels);
            resp.setStatus(0);
            resp.setData(applyDataModels);
            resp.setMessage("数据获取成功");
        } else {
            resp.setStatus(0);
            resp.setMessage("列表数据为空");
            resp.setData(applyDataModels);
        }
        return resp;
    }

    /**
     * 获取用户待审核列表
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/getPendingInfo"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<List<ApplyDataModel>> getPendingInfo(@RequestBody ApplyReq req) {
        BaseResp resp = new BaseResp();
        if (req.getUserId() == null) {
            resp.setStatus(-1);
            resp.setMessage("参数错误");
            return resp;
        }
        List<ApplyDataModel> pendingList = this.booksBorrowService.getPendingList(req.getUserId());
        if (pendingList != null && pendingList.size() > 0) {
            selectBookData(pendingList);
            resp.setStatus(0);
            resp.setData(pendingList);
            resp.setMessage("数据获取成功");
        } else {
            resp.setMessage("列表为空");
            resp.setStatus(0);
            resp.setData(pendingList);
        }
        return resp;
    }

    /**
     * 审核人审核通过操作接口
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/confirmBorrow"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp confirmBorrow(@RequestBody ApplyReq req) {
        BaseResp resp = new BaseResp();
        if (req.getUserId() == null || req.getId() == null) {
            resp.setStatus(-1);
            resp.setMessage("参数错误");
            return resp;
        }
        PendDataModel model = this.booksBorrowService.getPendingDataById(req.getId());
        if (model != null) {
            if (req.getUserId() != model.getHolder().intValue()) {
                resp.setStatus(-1);
                resp.setMessage("当前用户无权限进行操作");
                return resp;
            }
            //申请状态为待审核
            if (model.getApplyType() == 0) {
                List<BorrowDataModel> modelList = this.booksBorrowService.getReturnDataByBookId(model.getCreate_user(), model.getBookid());
                //查看书籍状态是否可借
                if (model.getBookStatus() == STATUS_BOOK_NORMAL && modelList.size() == 0) {
                    this.booksBorrowService.modifyBorrowBookData(model.getBookid(), model.getCreate_user());
                    this.booksBorrowService.modifyBorrowApplyData(model.getId(), DateUtil.getNow());
                    this.booksBorrowService.addBorrowData(model.getBookid(), model.getCreate_user());
                    resp.setStatus(0);
                    resp.setMessage("操作成功");
                    return resp;
                } else if (model.getBookStatus() == STATUS_BOOK_BORROW) {
                    resp.setStatus(-1);
                    resp.setMessage("对应书籍已经被借出，操作失败");
                    return resp;
                } else if (model.getBookStatus() == STATUS_BOOK_REMOVE) {
                    resp.setStatus(-1);
                    resp.setMessage("对应书籍已经下架，操作失败");
                    return resp;
                }
            } else {
                resp.setStatus(-1);
                resp.setMessage("该申请已通过审核，不允许重复审核");
                return resp;
            }
        }
        resp.setStatus(-1);
        resp.setMessage("书籍状态有误");
        return resp;
    }

    /**
     * 查看当前用户对应当前书籍的归还信息
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/getReturnDataByBookId"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<List<BorrowDataModel>> getReturnDataByBookId(@RequestBody ApplyReq req) {
        BaseResp resp = new BaseResp();
        if (req.getUserId() == null || req.getBookId() == null) {
            resp.setStatus(-1);
            resp.setMessage("参数错误");
            return resp;
        }
        List<BorrowDataModel> returnDataList = this.booksBorrowService.getReturnDataByBookId(req.getUserId(), req.getBookId());
        if (returnDataList != null && returnDataList.size() == 1) {
            resp.setStatus(0);
            resp.setData(returnDataList);
            resp.setMessage("数据获取成功");
        } else {
            if (returnDataList != null && returnDataList.size() > 1) {
                resp.setMessage("数据库错误请通知管理员");
                resp.setStatus(-1);
                return resp;
            }
            resp.setStatus(0);
            resp.setMessage("数据为空");
            resp.setData(returnDataList);
        }
        return resp;
    }

    /**
     * 查看当前用户可归还图书列表数据接口
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/getReturnBookList"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<List<BorrowDataModel>> getReturnBookList(@RequestBody ApplyReq req) {
        BaseResp resp = new BaseResp();
        if (req.getUserId() == null) {
            resp.setStatus(-1);
            resp.setMessage("参数错误");
            return resp;
        }
        List<BorrowDataModel> modelList = this.booksBorrowService.getCanReturnBookList(req.getUserId());
        if (modelList != null && modelList.size() > 0) {
            resp.setStatus(0);
            resp.setData(modelList);
            resp.setMessage("数据获取成功");
        } else {
            resp.setMessage("列表为空");
            resp.setStatus(0);
            resp.setData(modelList);
        }
        return resp;
    }

    /**
     * 归还图书接口
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/returnBook"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp returnBook(@RequestBody ApplyReq req) {
        BaseResp resp = new BaseResp();
        if (req.getId() == null || req.getUserId() == null) {
            resp.setStatus(-1);
            resp.setMessage("参数错误");
            return resp;
        }
        BorrowDataModel model = this.booksBorrowService.getCanReturnBookData(req.getId());
        if (model.getType() == 0) {
            if (model.getUserid().intValue() != req.getUserId().intValue()) {
                resp.setStatus(-1);
                resp.setMessage("当前用户无权限进行该操作");
                return resp;
            }
            this.booksBorrowService.returnBook(req.getId());
            resp.setStatus(0);
            resp.setMessage("归还成功");
            return resp;
        } else if (model.getType() == 1) {
            resp.setStatus(-1);
            resp.setMessage("书籍已经归还，操作失败");
            return resp;
        }
        resp.setStatus(-1);
        resp.setMessage("书籍状态有误");
        return resp;
    }

    /**
     * 获取书籍详情
     *
     * @param originList
     */
    private void selectBookData(List<ApplyDataModel> originList) {
        if (originList != null && originList.size() > 0) {
            for (ApplyDataModel model : originList) {
                BookModel booksInfo = this.booksInfoService.getBookDetail(model.getBookid());
                if (booksInfo != null) {
                    model.setBookModel(booksInfo);
                }
            }
        }
    }

    /**
     * 获取对应书籍当前用户的已续借次数
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/getRenewCount"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<BorrowDataModel> getRenewCount(@RequestBody ApplyReq req) {
        BaseResp resp = new BaseResp();
        if (req.getUserId() == null || req.getBookId() == null) {
            resp.setMessage("参数错误");
            resp.setStatus(-1);
            return resp;
        }
        List<BorrowDataModel> returnDataList = this.booksBorrowService.getReturnDataByBookId(req.getUserId(), req.getBookId());
        if (returnDataList != null && returnDataList.size() == 1) {
            resp.setStatus(0);
            BorrowDataModel resultModel = new BorrowDataModel();
            resultModel.setRenew_count(returnDataList.get(0).getRenew_count());
            resp.setData(resultModel);
            resp.setMessage("数据获取成功");
        } else {
            if (returnDataList != null && returnDataList.size() > 1) {
                resp.setMessage("数据库错误请通知管理员");
                resp.setStatus(-1);
                return resp;
            }
            resp.setMessage("数据为空");
            resp.setStatus(0);
            resp.setData(returnDataList);
        }
        return resp;
    }

    /**
     * 书籍续借
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/renewBook"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp renewBook(@RequestBody ApplyReq req) {
        BaseResp resp = new BaseResp();
        Integer id = req.getId();
        Integer userId = req.getUserId();
        Integer bookId = req.getBookId();

        if (id != null) {
            BorrowDataModel model = this.booksBorrowService.getCanReturnBookData(id);
            resp = renewBook(model, resp, userId);
            if (resp != null) {
                return resp;
            }
        } else if (userId != null && bookId != null) {
            List<BorrowDataModel> returnDataList = this.booksBorrowService.getReturnDataByBookId(userId, bookId);
            if (returnDataList != null && returnDataList.size() == 1) {
                BorrowDataModel model = returnDataList.get(0);
                resp = renewBook(model, resp, userId);
                if (resp != null) {
                    return resp;
                }
            } else if (returnDataList != null && returnDataList.size() > 1) {
                resp.setMessage("数据库错误请通知管理员");
                resp.setStatus(-1);
                return resp;
            }
        } else {
            resp.setStatus(-1);
            resp.setMessage("参数错误");
            return resp;
        }
        if (resp == null) {
            resp = new BaseResp();
        }
        resp.setStatus(-1);
        resp.setMessage("不存在对应的借阅中记录，操作失败");
        return resp;
    }

    private BaseResp renewBook(BorrowDataModel model, BaseResp resp, Integer userId) {
        if (model != null) {
            if (model.getType() == 0) {
                if (model.getUserid().intValue() != userId.intValue()) {
                    resp.setStatus(-1);
                    resp.setMessage("当前用户无权限进行该操作");
                    return resp;
                }
                if (model.getRenew_count() >= 2) {
                    resp.setStatus(-1);
                    resp.setMessage("借阅次数已达上限");
                    return resp;
                }
                if (model.getId() == null || TextUtils.isEmpty(model.getExpect_return_date()) || model.getRenew_count() == null) {
                    resp.setStatus(-1);
                    resp.setMessage("数据库数据错误请联系管理员");
                    return resp;
                }
                int result = this.booksBorrowService.renewBookById(model.getId(), DateUtil.getNowLaterDate(model.getExpect_return_date(), 15), model.getRenew_count() + 1);
                resp.setStatus(result == 1 ? 0 : -1);
                resp.setMessage(result == 1 ? "续借成功" : "续借失败");
                return resp;
            } else if (model.getType() == 1) {
                resp.setStatus(-1);
                resp.setMessage("书籍已经归还，续借失败");
                return resp;
            }
        }
        return null;
    }

}
