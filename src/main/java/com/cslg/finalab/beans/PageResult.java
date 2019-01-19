package com.cslg.finalab.beans;

import com.google.common.collect.Lists;

import java.util.List;

public class PageResult<T> {

    private int total;

    private List<T> list = Lists.newArrayList();

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
