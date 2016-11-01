package com.mstring.andtest.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 李宗源 on 2016/10/31.
 */

public class LeLiveGetPushUrlBean implements Serializable {
    private int liveNum;//机位数量
    private ArrayList<LeLiveLiveBean> lives;//机位数组
    private long startTime;
    private long endTime;

    public int getLiveNum() {
        return liveNum;
    }

    public void setLiveNum(int liveNum) {
        this.liveNum = liveNum;
    }

    public ArrayList<LeLiveLiveBean> getLives() {
        return lives;
    }

    public void setLives(ArrayList<LeLiveLiveBean> lives) {
        this.lives = lives;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "LeLiveGetPushUrlBean{" +
                "liveNum=" + liveNum +
                ", lives=" + lives +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
