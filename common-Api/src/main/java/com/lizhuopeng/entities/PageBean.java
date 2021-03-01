package com.lizhuopeng.entities;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable {
    private Integer offset;//当前页数
    private Integer pageSize;//每页显示数
    private Integer totalPage;//总页数
    private Integer total;//总记录数
    private List<T> pageRecord;//当前页面的数据集合




    public PageBean(Integer offset, Integer pageSize) {
        this.offset = offset;
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getPageRecord() {
        return pageRecord;
    }

    public void setPageRecord(List<T> pageRecord) {
        this.pageRecord = pageRecord;
    }
}
