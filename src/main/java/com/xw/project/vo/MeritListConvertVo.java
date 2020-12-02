package com.xw.project.vo;

import com.xw.project.entity.MeritList;

import java.util.List;

public class MeritListConvertVo {
    private List<MeritList> list;
    private int total;

    public List<MeritList> getList() {
        return list;
    }

    public void setList(List<MeritList> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
