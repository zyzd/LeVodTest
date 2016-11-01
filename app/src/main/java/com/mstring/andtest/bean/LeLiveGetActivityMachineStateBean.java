package com.mstring.andtest.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 李宗源 on 2016/10/31.
 */

public class LeLiveGetActivityMachineStateBean implements Serializable {
    private String activityId;//活动ID
    private int liveNum;//机位数量
    private ArrayList<LeLiveLiveInfo> lives;//	机位信息列表

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public int getLiveNum() {
        return liveNum;
    }

    public void setLiveNum(int liveNum) {
        this.liveNum = liveNum;
    }

    public ArrayList<LeLiveLiveInfo> getLives() {
        return lives;
    }

    public void setLives(ArrayList<LeLiveLiveInfo> lives) {
        this.lives = lives;
    }

    @Override
    public String toString() {
        return "LeLiveGetActivityMachineStateBean{" +
                "activityId='" + activityId + '\'' +
                ", liveNum=" + liveNum +
                ", lives=" + lives +
                '}';
    }

    public class LeLiveLiveInfo implements Serializable {
        private String liveId;//直播ID。机位的唯一对应的标识
        private String machine;//机位位置。取值范围 1-4
        private String status;//机位状态。0:无信号   1:有信号

        public String getLiveId() {
            return liveId;
        }

        public void setLiveId(String liveId) {
            this.liveId = liveId;
        }

        public String getMachine() {
            return machine;
        }

        public void setMachine(String machine) {
            this.machine = machine;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "LeLiveLiveInfo{" +
                    "liveId='" + liveId + '\'' +
                    ", machine='" + machine + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
}
