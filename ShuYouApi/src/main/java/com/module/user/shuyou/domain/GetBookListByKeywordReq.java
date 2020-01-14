package com.module.user.shuyou.domain;

public class GetBookListByKeywordReq {
    private String keyword;
    private Integer pageNo;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

}
