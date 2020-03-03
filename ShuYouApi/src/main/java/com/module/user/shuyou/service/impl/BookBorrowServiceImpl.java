package com.module.user.shuyou.service.impl;

import com.module.user.shuyou.dataModel.ApplyDataModel;
import com.module.user.shuyou.dataModel.BorrowDataModel;
import com.module.user.shuyou.dataModel.PendDataModel;
import com.module.user.shuyou.mapper.BooksBorrowMapper;
import com.module.user.shuyou.service.BookBorrowService;
import com.module.user.shuyou.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("bookBorrowServiceImpl")
public class BookBorrowServiceImpl implements BookBorrowService {
    @Autowired
    BooksBorrowMapper booksBorrowMapper;

    public List<ApplyDataModel> getApplyListById(Integer id) {
        return this.booksBorrowMapper.selectApplyListById(id);
    }

    public List<ApplyDataModel> getApplyList(Integer userId) {
        return this.booksBorrowMapper.selectApplyListByUserId(userId);
    }

    public List<ApplyDataModel> getApplyingList(Integer userId) {
        return this.booksBorrowMapper.selectApplyingByUserId(userId);
    }

    public List<ApplyDataModel> getPendingList(Integer userId) {
        return this.booksBorrowMapper.selectPendingListByUserId(userId);
    }

    public PendDataModel getPendingDataById(Integer id) {
        return this.booksBorrowMapper.selectPendingDataById(id);
    }

    public List<BorrowDataModel> getCanReturnBookList(Integer userId) {
        return this.booksBorrowMapper.getCanReturnBookList(userId);
    }

    public List<BorrowDataModel> getReturnDataByBookId(Integer userId, Integer bookId) {
        Map map = new HashMap();
        map.put("userid", userId);
        map.put("bookid", bookId);
        return this.booksBorrowMapper.getReturnDataByBookId(map);
    }

    public BorrowDataModel getCanReturnBookData(Integer id) {
        return this.booksBorrowMapper.getCanReturnBookData(id);
    }

    public List<ApplyDataModel> getApplyList(Integer userId, Integer bookId) {
        Map map = new HashMap();
        map.put("userid", userId);
        map.put("bookid", bookId);
        return this.booksBorrowMapper.selectApplyList(map);
    }

    public int applyBook(Integer userId, Integer bookId) {
        Map dataMap = new HashMap();
        dataMap.put("createtime", DateUtil.getNow());
        dataMap.put("create_user", userId);
        dataMap.put("bookid", bookId);
        return this.booksBorrowMapper.insertApply(dataMap);
    }

    public int cancelBookApplyData(Integer id) {
        return this.booksBorrowMapper.cancelApplyById(id);
    }

    public int modifyBorrowBookData(Integer bookId, Integer borrowUserId) {
        Map dataMap = new HashMap();
        dataMap.put("bookId", bookId);
        dataMap.put("borrowUserId", borrowUserId);
        return this.booksBorrowMapper.modifyBorrowBookData(dataMap);
    }

    public int modifyBorrowApplyData(Integer id, String handleTime) {
        Map dataMap = new HashMap();
        dataMap.put("id", id);
        dataMap.put("handleTime", handleTime);
        return this.booksBorrowMapper.modifyBorrowApplyData(dataMap);
    }

    public int addBorrowData(Integer bookId, Integer borrowUserId) {
        Map dataMap = new HashMap();
        dataMap.put("bookid", bookId);
        dataMap.put("userid", borrowUserId);
        dataMap.put("borrow_date", DateUtil.getNow());
        dataMap.put("expect_date", DateUtil.getNowLaterDate(15));
        return this.booksBorrowMapper.addBorrowData(dataMap);
    }

    public int confirmBorrowBook(Integer id) {
        return 0;
    }

    public int returnBook(Integer id) {
        return this.booksBorrowMapper.returnBookById(id);
    }

    public int renewBookById(Integer id, String expectDate, Integer renewCount) {
        Map dataMap = new HashMap();
        dataMap.put("id", id);
        dataMap.put("expectDate", expectDate);
        dataMap.put("renewCount", renewCount);
        return this.booksBorrowMapper.renewBookById(dataMap);
    }
}
