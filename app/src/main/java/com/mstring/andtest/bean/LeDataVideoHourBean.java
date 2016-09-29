package com.mstring.andtest.bean;

import java.io.Serializable;

/**
 * 获取以小时为单位的视频数据
 * Created by 李宗源 on 2016/9/29.
 */

public class LeDataVideoHourBean implements Serializable {
    private String hour;
    private String video_id;
    private String video_view;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
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
        return "LeDataVideoHourBean{" +
                "hour='" + hour + '\'' +
                ", video_id='" + video_id + '\'' +
                ", video_view='" + video_view + '\'' +
                '}';
    }
}
