package com.mstring.andtest.bean;

import java.io.Serializable;

/**
 * 获取以天为单位的所有数据
 * Created by 李宗源 on 2016/9/29.
 */

public class LeDataTotalDateBean implements Serializable {
    private String date;
    private String total_view;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal_view() {
        return total_view;
    }

    public void setTotal_view(String total_view) {
        this.total_view = total_view;
    }

    @Override
    public String toString() {
        return "LeDataTotalDateBean{" +
                "date='" + date + '\'' +
                ", total_view='" + total_view + '\'' +
                '}';
    }
}
