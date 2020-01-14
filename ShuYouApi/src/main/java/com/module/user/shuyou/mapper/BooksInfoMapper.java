package com.module.user.shuyou.mapper;

import com.module.user.shuyou.dataModel.AddBookDataModel;
import com.module.user.shuyou.dataModel.BookModel;
import com.module.user.shuyou.dataModel.Comment;

import java.util.List;
import java.util.Map;

public interface BooksInfoMapper {
    int deleteByPrimaryKey(Integer paramInteger);

    int insert(AddBookDataModel addBookDataModel);

    BookModel selectByPrimaryKey(Integer id);

    List<Comment> getBookCommentById(Integer id);

    int updateByPrimaryKeySelective(BookModel paramGoodsInfo);

    int updateByPrimaryKey(BookModel paramGoodsInfo);

    List<BookModel> selectAllBooksList();

    List<BookModel> selectBooksList(Map paramMap);

    List<BookModel> selectAllBooksListByType(Integer type);

    List<BookModel> selectBooksListByType(Map paramMap);

    List<BookModel> selectBooksListByKeyword(Map paramMap);

    List<BookModel> selectAllByKeyword(String keyword);
}
