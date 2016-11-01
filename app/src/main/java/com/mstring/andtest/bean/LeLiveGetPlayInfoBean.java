package com.mstring.andtest.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 李宗源 on 2016/11/1.
 */

public class LeLiveGetPlayInfoBean implements Serializable {
    private String activityId;
    private ArrayList<MachineInfo> machineInfo;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public ArrayList<MachineInfo> getMachineInfo() {
        return machineInfo;
    }

    public void setMachineInfo(ArrayList<MachineInfo> machineInfo) {
        this.machineInfo = machineInfo;
    }

    @Override
    public String toString() {
        return "LeLiveGetPlayInfoBean{" +
                "activityId='" + activityId + '\'' +
                ", machineInfo=" + machineInfo +
                '}';
    }

    static class MachineInfo implements Serializable {
        private String videoId;
        private String videoUnique;

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getVideoUnique() {
            return videoUnique;
        }

        public void setVideoUnique(String videoUnique) {
            this.videoUnique = videoUnique;
        }

        @Override
        public String toString() {
            return "MachineInfo{" +
                    "videoId='" + videoId + '\'' +
                    ", videoUnique='" + videoUnique + '\'' +
                    '}';
        }
    }
}
