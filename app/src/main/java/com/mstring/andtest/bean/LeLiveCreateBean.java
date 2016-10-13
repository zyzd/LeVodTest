package com.mstring.andtest.bean;

import java.io.Serializable;

/**
 * Created by 李宗源 on 2016/10/11.
 * 创建标准直播活动的接口
 */

public class LeLiveCreateBean implements Serializable {
    private String activityId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "LeLiveCreateBean{" +
                "activityId='" + activityId + '\'' +
                '}';
    }
}
