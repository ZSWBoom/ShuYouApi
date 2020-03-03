package com.module.user.shuyou.domain;

public class GetBooksListReq extends BaseReq {
    private Integer type;
    private Integer pageNo;

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer categoryId) {
        this.type = categoryId;
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}