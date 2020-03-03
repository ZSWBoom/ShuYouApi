package com.module.user.shuyou.controller;

import com.module.user.shuyou.dataModel.BookModel;
import com.module.user.shuyou.dataModel.Comment;
import com.module.user.shuyou.dataModel.AddBookDataModel;
import com.module.user.shuyou.domain.BaseResp;
import com.module.user.shuyou.domain.GetBookListByKeywordReq;
import com.module.user.shuyou.domain.GetBooksDetailReq;
import com.module.user.shuyou.domain.GetBooksListReq;
import com.module.user.shuyou.service.BookDataService;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(produces = {"application/json;charset=UTF-8"}, value = {"/bookData"})
public class BookDataController extends BaseController {
    @Autowired
    BookDataService booksInfoService;

    /**
     * 查询数据库所有书籍数据列表
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/getBooks"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<List<BookModel>> getBooks(@RequestBody GetBooksListReq req) {
        BaseResp resp = new BaseResp();
        if (req.getPageNo() == null) {
            resp.setStatus(-1);
            resp.setMessage("参数错误");
            return resp;
        }

        List<BookModel> list = this.booksInfoService.getBookDataList(req.getPageNo());
        if ((list == null) || (list.size() == 0)) {
            resp.setStatus(0);
            resp.setMessage("列表为空");
            return resp;
        }

        for (BookModel info : list) {
            info.setMaxPage(this.booksInfoService.getAllGoodsList().size() / 6 + 1);
        }

        resp.setStatus(0);
        resp.setMessage("列表获取成功");
        resp.setData(list);
        return resp;
    }

    /**
     * 根据书籍类型查询书籍列表
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/getBookByType"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<List<BookModel>> getBookByType(@RequestBody GetBooksListReq req) {
        BaseResp resp = new BaseResp();
        if (req.getType() == null || req.getPageNo() == null) {
            resp.setStatus(-1);
            resp.setMessage("参数错误");
            return resp;
        }

        List<BookModel> list = this.booksInfoService.getBookDataListByType(req.getType(), req.getPageNo());
        if ((list == null) || (list.size() == 0)) {
            resp.setStatus(0);
            resp.setMessage("列表为空");
            return resp;
        }

        for (BookModel info : list) {
            info.setMaxPage(this.booksInfoService.getAllGoodsListByType(req.getType()).size() / 6 + 1);
        }

        resp.setStatus(0);
        resp.setMessage("列表获取成功");
        resp.setData(list);
        return resp;
    }

    /**
     * 查询书籍详细内容数据
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/getBooksDetail"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<BookModel> getBooksDetail(@RequestBody GetBooksDetailReq req) {
        BaseResp resp = new BaseResp();
        if (req.getId() == null) {
            resp.setStatus(-1);
            resp.setMessage("参数错误");
            return resp;
        }

        BookModel booksInfo = this.booksInfoService.getBookDetail(req.getId());
        if (booksInfo == null) {
            resp.setStatus(0);
            resp.setMessage("数据为空");
            return resp;
        }
        List<Comment> comments = this.booksInfoService.getBookCommentById(booksInfo.getId());
        booksInfo.setComment(comments);

        resp.setStatus(0);
        resp.setMessage("列表获取成功");
        resp.setData(booksInfo);
        return resp;
    }

    /**
     * 书名模糊查询
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/getBooksListByKeyword"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<List<BookModel>> getBooksListByKeyword(@RequestBody GetBookListByKeywordReq req) {
        BaseResp resp = new BaseResp();
        if (req.getKeyword() == null || req.getPageNo() == null) {
            resp.setStatus(-1);
            resp.setMessage("参数错误");
            return resp;
        }

        List<BookModel> list = this.booksInfoService.getBookListByKeyword(req.getKeyword(), req.getPageNo());
        if ((list == null) || (list.size() == 0)) {
            resp.setStatus(0);
            resp.setMessage("列表为空");
            return resp;
        }

        for (BookModel info : list) {
            info.setMaxPage(Integer.valueOf(this.booksInfoService.getAllByKeyword(req.getKeyword()).size() / 6 + 1));
        }

        resp.setStatus(0);
        resp.setMessage("列表获取成功");
        resp.setData(list);
        return resp;
    }

    /**
     * 添加书籍
     */
    @RequestMapping(value = {"/addBook"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<Integer> addBook(@RequestParam("owner") Integer owner,
                                     @RequestParam("bookName") String bookName,
                                     @RequestParam("type") Integer type,
                                     @RequestParam("des") String des,
                                     @RequestParam("code") String code,
                                     @RequestParam("imgFile") CommonsMultipartFile fileUpload,
                                     @RequestParam("price") String price,
                                     @RequestParam("bookAuthor") String bookAuthor) {
        BaseResp resp = new BaseResp();
        if (TextUtils.isEmpty(code)) {
            resp.setStatus(-1);
            resp.setMessage("书籍二维码不允许为空");
            return resp;
        }
        if (TextUtils.isEmpty(bookName)) {
            resp.setStatus(-1);
            resp.setMessage("书籍名称不允许为空");
            return resp;
        }
        if (owner == null) {
            resp.setStatus(-1);
            resp.setMessage("书籍拥有者不允许为空");
            return resp;
        }
        if (type == null) {
            resp.setStatus(-1);
            resp.setMessage("书籍类型不允许为空");
            return resp;
        }
        if (TextUtils.isEmpty(price)) {
            resp.setStatus(-1);
            resp.setMessage("书籍价格不允许为空");
            return resp;
        }

        AddBookDataModel addBookDataModel = new AddBookDataModel();
        String imgUrl;
        try {
            imgUrl = this.booksInfoService.uploadBookImg(fileUpload);
            addBookDataModel.setImgUrl(imgUrl);
        } catch (IOException e) {
            e.printStackTrace();
            resp.setStatus(-1);
            resp.setMessage("文件上传失败");
            return resp;
        }

        addBookDataModel.setOwner(owner);
        addBookDataModel.setBookName(bookName);
        addBookDataModel.setType(type);
        addBookDataModel.setDes(des);
        addBookDataModel.setCode(code);
        addBookDataModel.setPrice(price);
        addBookDataModel.setCreateDate(Calendar.getInstance().getTime());
        addBookDataModel.setBookAuthor(bookAuthor);
        addBookDataModel.setHot("0");
        this.booksInfoService.addBookData(addBookDataModel);
        resp.setStatus(0);
        resp.setMessage("数据上传成功");
        resp.setData(1);
        return resp;
    }
}
