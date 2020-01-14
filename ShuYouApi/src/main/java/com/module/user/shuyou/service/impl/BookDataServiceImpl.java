package com.module.user.shuyou.service.impl;

import com.module.user.shuyou.dataModel.AddBookDataModel;
import com.module.user.shuyou.dataModel.BookModel;
import com.module.user.shuyou.dataModel.Comment;
import com.module.user.shuyou.mapper.BooksInfoMapper;
import com.module.user.shuyou.service.BookDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("bookDataServiceImpl")
public class BookDataServiceImpl implements BookDataService {
    @Autowired
    BooksInfoMapper booksInfoMapper;

    public List<BookModel> getBookDataList(Integer pageNo) {
        Map map = new HashMap();
        map.put("beginIndex", Integer.valueOf((pageNo.intValue() - 1) * 6));
        map.put("pageSize", Integer.valueOf(6));
        return this.booksInfoMapper.selectBooksList(map);
    }

    public List<BookModel> getAllGoodsList() {
        return this.booksInfoMapper.selectAllBooksList();
    }

    public List<BookModel> getBookDataListByType(Integer type, Integer pageNo) {
        Map map = new HashMap();
        map.put("type", type);
        map.put("beginIndex", Integer.valueOf((pageNo.intValue() - 1) * 6));
        map.put("pageSize", Integer.valueOf(6));
        return this.booksInfoMapper.selectBooksListByType(map);
    }

    public List<BookModel> getAllGoodsListByType(Integer type) {
        return this.booksInfoMapper.selectAllBooksListByType(type);
    }

    public BookModel getBookDetail(Integer id) {
        return this.booksInfoMapper.selectByPrimaryKey(id);
    }

    public List<Comment> getBookCommentById(Integer id) {
        return this.booksInfoMapper.getBookCommentById(id);
    }

    public List<BookModel> getBookListByKeyword(String keyword, Integer pageNo) {
        Map map = new HashMap();
        map.put("keyword", keyword);
        map.put("beginIndex", Integer.valueOf((pageNo.intValue() - 1) * 6));
        map.put("pageSize", Integer.valueOf(6));
        return this.booksInfoMapper.selectBooksListByKeyword(map);
    }

    public List<BookModel> getAllByKeyword(String paramString) {
        return this.booksInfoMapper.selectAllByKeyword(paramString);
    }

    public String uploadBookImg(CommonsMultipartFile file) throws IOException {
        // 得到图片的原始文件名
        String originalName = file.getOriginalFilename();
        // 指定带盘符的路径, 物理路径
        String realPath = "C://data//file//";
        /**
         * 为了处理出现重名现象, 将原始文件名去掉,
         * 通过UUID算法生成新的文件名
         */
        String uuidName = UUID.randomUUID().toString();
        // uuid名称加上文件的后缀名
        String newFile = uuidName + originalName.substring(originalName.lastIndexOf("."));
        // 创建File文件
        File newFileData = new File(realPath + newFile);
        // 将图片写入到具体的位置
        file.transferTo(newFileData);
        // 将文件名保存到数据库
        return "http://47.93.58.173:8080/file/"+newFile;
    }

    public int addBookData(AddBookDataModel addBookDataModel) {
        return this.booksInfoMapper.insert(addBookDataModel);
    }
}
