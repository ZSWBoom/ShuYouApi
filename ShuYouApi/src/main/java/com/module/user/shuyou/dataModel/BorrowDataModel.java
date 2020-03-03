package com.module.user.shuyou.dataModel;

public class BorrowDataModel {
    private Integer id;
    private Integer userid;
    private Integer bookid;
    private String borrow_date;
    private String expect_return_date;
    private String real_return_date;
    private Integer type;
    private Integer renew_count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }

    public String getExpect_return_date() {
        return expect_return_date;
    }

    public void setExpect_return_date(String expect_return_date) {
        this.expect_return_date = expect_return_date;
    }

    public String getReal_return_date() {
        return real_return_date;
    }

    public void setReal_return_date(String real_return_date) {
        this.real_return_date = real_return_date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRenew_count() {
        return renew_count;
    }

    public void setRenew_count(Integer renew_count) {
        this.renew_count = renew_count;
    }
}
