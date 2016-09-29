package com.mstring.andtest.bean;

import java.io.Serializable;

/**
 * 获取以天为单位的视频数据
 * Created by 李宗源 on 2016/9/29.
 */

public class LeDataVideoDateBean implements Serializable {
    private String date;
    private String video_id;
    private String video_view;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getVideo_view() {
        return video_view;
    }

    public void setVideo_view(String video_view) {
        this.video_view = video_view;
    }

    @Override
    public String toString() {
        return "LeDataVideoDateBean{" +
                "date='" + date + '\'' +
                ", video_id='" + video_id + '\'' +
                ", video_view='" + video_view + '\'' +
                '}';
    }
}
