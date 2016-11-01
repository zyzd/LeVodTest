package com.mstring.andtest.bean;

import com.google.gson.internal.Streams;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 李宗源 on 2016/10/31.
 */

public class LeLiveStreaminfoSearchBean implements Serializable {
    private String activityId;
    private int liveNum;
    private ArrayList<LiveInfo> lives;

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

    public ArrayList<LiveInfo> getLives() {
        return lives;
    }

    public void setLives(ArrayList<LiveInfo> lives) {
        this.lives = lives;
    }

    @Override
    public String toString() {
        return "LeLiveStreaminfoSearchBean{" +
                "activityId='" + activityId + '\'' +
                ", liveNum=" + liveNum +
                ", lives=" + lives +
                '}';
    }

    static class LiveInfo implements Serializable {
        private String liveId;
        private ArrayList<StreamInfo> streams;
        private int machine;

        public String getLiveId() {
            return liveId;
        }

        public void setLiveId(String liveId) {
            this.liveId = liveId;
        }

        public ArrayList<StreamInfo> getStreams() {
            return streams;
        }

        public void setStreams(ArrayList<StreamInfo> streams) {
            this.streams = streams;
        }

        public int getMachine() {
            return machine;
        }

        public void setMachine(int machine) {
            this.machine = machine;
        }

        @Override
        public String toString() {
            return "LiveInfo{" +
                    "liveId='" + liveId + '\'' +
                    ", streams=" + streams +
                    ", machine=" + machine +
                    '}';
        }
    }

    static class StreamInfo implements Serializable {
        private String streamId;
        private String codeRateType;

        public String getStreamId() {
            return streamId;
        }

        public void setStreamId(String streamId) {
            this.streamId = streamId;
        }

        public String getCodeRateType() {
            return codeRateType;
        }

        public void setCodeRateType(String codeRateType) {
            this.codeRateType = codeRateType;
        }

        @Override
        public String toString() {
            return "StreamInfo{" +
                    "streamId='" + streamId + '\'' +
                    ", codeRateType='" + codeRateType + '\'' +
                    '}';
        }
    }
}
