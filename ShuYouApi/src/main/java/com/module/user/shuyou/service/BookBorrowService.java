package com.module.user.shuyou.service;

import com.module.user.shuyou.dataModel.ApplyDataModel;
import com.module.user.shuyou.dataModel.BorrowDataModel;
import com.module.user.shuyou.dataModel.PendDataModel;

import java.util.List;

public interface BookBorrowService {

    List<ApplyDataModel> getApplyListById(Integer id);

    List<ApplyDataModel> getApplyList(Integer userId);

    List<ApplyDataModel> getApplyingList(Integer userId);

    List<ApplyDataModel> getApplyList(Integer userId, Integer bookId);

    List<ApplyDataModel> getPendingList(Integer userId);

    PendDataModel getPendingDataById(Integer id);

    List<BorrowDataModel> getCanReturnBookList(Integer userId);

    List<BorrowDataModel> getReturnDataByBookId(Integer userId, Integer bookId);

    BorrowDataModel getCanReturnBookData(Integer id);

    int renewBookById(Integer d, String expectReturnDate, Integer renewCount);

    int applyBook(Integer userId, Integer bookId);

    int cancelBookApplyData(Integer id);

    int modifyBorrowBookData(Integer bookId, Integer borrowUserId);

    int modifyBorrowApplyData(Integer id, String handleTime);

    int addBorrowData(Integer bookId, Integer borrowUserId);

    int confirmBorrowBook(Integer id);

    int returnBook(Integer id);



}
