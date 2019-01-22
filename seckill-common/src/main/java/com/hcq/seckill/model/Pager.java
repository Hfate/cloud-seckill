package com.hcq.seckill.model;

import java.util.List;

/**
 * json数据分页
 *
 * @param <T>
 * @author admin
 */
public class Pager<T> {
    /**
     * 总记录
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 数据列表
     */
    private List<T> list;

    public Pager() {
    }

    public Pager(List<T> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
