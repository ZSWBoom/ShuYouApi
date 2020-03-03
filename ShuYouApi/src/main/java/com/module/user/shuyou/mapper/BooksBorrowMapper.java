package com.module.user.shuyou.mapper;

import com.module.user.shuyou.dataModel.ApplyDataModel;
import com.module.user.shuyou.dataModel.BorrowDataModel;
import com.module.user.shuyou.dataModel.PendDataModel;

import java.util.List;
import java.util.Map;

public interface BooksBorrowMapper {

    List<ApplyDataModel> selectApplyListById(Integer id);

    List<ApplyDataModel> selectApplyListByUserId(Integer userId);

    List<ApplyDataModel> selectApplyingByUserId(Integer userId);

    List<ApplyDataModel> selectApplyList(Map paramMap);

    int insertApply(Map paramMap);

    int cancelApplyById(Integer id);

    List<ApplyDataModel> selectPendingListByUserId(Integer userId);

    PendDataModel selectPendingDataById(Integer id);

    List<BorrowDataModel> getCanReturnBookList(Integer userId);

    List<BorrowDataModel> getReturnDataByBookId(Map paramMap);

    BorrowDataModel getCanReturnBookData(Integer id);

    int modifyBorrowBookData(Map paramMap);

    int modifyBorrowApplyData(Map paramMap);

    int addBorrowData(Map paramMap);

    int confirmApplyById(Integer id);

    int insertBorrowData(Map paramMap);

    int returnBookById(Integer id);

    int renewBookById(Map paramMap);
}
