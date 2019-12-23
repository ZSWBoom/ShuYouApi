package com.module.user.shuyou.service;

import com.module.user.shuyou.dataModel.BookModel;

import java.util.List;

public interface BookDataService {

    List<BookModel> getBookDataList(Integer paramInteger);
}
