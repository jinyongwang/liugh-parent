package com.liugh.base;


import java.util.List;

/**
 * @author liugh
 * @since 2018-05-03
 */
public class PageResult<T> {
    private Integer total;
    private Integer pageIndex;
    private Integer pageSize;
    private List<T> list;

    public PageResult() {
    }

    public PageResult(Integer total, Integer pageIndex, Integer pageSize, List<T> list) {
        this.total = total;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

