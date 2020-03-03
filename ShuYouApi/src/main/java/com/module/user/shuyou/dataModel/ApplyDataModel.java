package com.module.user.shuyou.dataModel;

public class ApplyDataModel {
    private Integer id;
    private Integer bookid;
    private Integer create_user;
    private Integer type;
    private String createtime;
    private String handletime;


    private BookModel bookModel;

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Integer create_user) {
        this.create_user = create_user;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getHandletime() {
        return handletime;
    }

    public void setHandletime(String handletime) {
        this.handletime = handletime;
    }

    public BookModel getBookModel() {
        return bookModel;
    }

    public void setBookModel(BookModel bookModel) {
        this.bookModel = bookModel;
    }

}
