package com.module.user.shuyou.service;

import com.module.user.shuyou.dataModel.AddBookDataModel;
import com.module.user.shuyou.dataModel.BookModel;
import com.module.user.shuyou.dataModel.Comment;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookDataService {

    List<BookModel> getBookDataList(Integer pageNo);

    List<BookModel> getAllGoodsList();

    List<BookModel> getBookDataListByType(Integer categoryId, Integer pageNo);

    List<BookModel> getAllGoodsListByType(Integer paramInteger);

    BookModel getBookDetail(Integer paramInteger);

    List<Comment> getBookCommentById(Integer paramInteger);

    List<BookModel> getBookListByKeyword(String paramString, Integer paramInteger);

    List<BookModel> getAllByKeyword(String paramString);

    int addBookData(AddBookDataModel addBookDataModel);

    String uploadBookImg(CommonsMultipartFile file) throws IOException;


}
