package com.mstring.andtest.bean;

import java.io.Serializable;

/**
 * Created by 李宗源 on 2016/10/31.
 */

public class LeLiveCreateRecTaskBean implements Serializable {
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "LeLiveCreateRecTaskBean{" +
                "taskId='" + taskId + '\'' +
                '}';
    }
}
