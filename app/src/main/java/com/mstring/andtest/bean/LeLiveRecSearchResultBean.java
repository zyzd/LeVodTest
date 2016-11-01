package com.mstring.andtest.bean;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 李宗源 on 2016/11/1.
 */

public class LeLiveRecSearchResultBean implements Serializable {
    private int total;//符合条件的总条数
    private ArrayList<Row> rows;//分页的列表

    @Override
    public String toString() {
        return "LeLiveRecSearchResultBean{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }

    static class Row implements Serializable {
        private String belongUserId;//所属用户id
        private String businessLine;
        private String liveId;//直播ID
        private int needRemoveTs;

        private String outputCodeRateTypes;
        private String recTimes;
        private int recType;
        private int status;//录制任务状态。 1、5 新建； 2 执行中； 4 不录制；3 录制完成（生成云点播视频后则变为已完成）； 6 录制失败；7 转码完成；8 转码失败

        private long submitTime;
        private int taskId;//任务ID
        private long videoCreateTime;
        private String videoName;

        public String getBelongUserId() {
            return belongUserId;
        }

        public void setBelongUserId(String belongUserId) {
            this.belongUserId = belongUserId;
        }

        public String getBusinessLine() {
            return businessLine;
        }

        public void setBusinessLine(String businessLine) {
            this.businessLine = businessLine;
        }

        public String getLiveId() {
            return liveId;
        }

        public void setLiveId(String liveId) {
            this.liveId = liveId;
        }

        public int getNeedRemoveTs() {
            return needRemoveTs;
        }

        public void setNeedRemoveTs(int needRemoveTs) {
            this.needRemoveTs = needRemoveTs;
        }

        public String getOutputCodeRateTypes() {
            return outputCodeRateTypes;
        }

        public void setOutputCodeRateTypes(String outputCodeRateTypes) {
            this.outputCodeRateTypes = outputCodeRateTypes;
        }

        public String getRecTimes() {
            return recTimes;
        }

        public void setRecTimes(String recTimes) {
            this.recTimes = recTimes;
        }

        public int getRecType() {
            return recType;
        }

        public void setRecType(int recType) {
            this.recType = recType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getSubmitTime() {
            return submitTime;
        }

        public void setSubmitTime(long submitTime) {
            this.submitTime = submitTime;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public long getVideoCreateTime() {
            return videoCreateTime;
        }

        public void setVideoCreateTime(long videoCreateTime) {
            this.videoCreateTime = videoCreateTime;
        }

        public String getVideoName() {
            return videoName;
        }

        public void setVideoName(String videoName) {
            this.videoName = videoName;
        }

        @Override
        public String toString() {
            return "Row{" +
                    "belongUserId='" + belongUserId + '\'' +
                    ", businessLine='" + businessLine + '\'' +
                    ", liveId='" + liveId + '\'' +
                    ", needRemoveTs=" + needRemoveTs +
                    ", outputCodeRateTypes='" + outputCodeRateTypes + '\'' +
                    ", recTimes='" + recTimes + '\'' +
                    ", recType=" + recType +
                    ", status=" + status +
                    ", submitTime=" + submitTime +
                    ", taskId=" + taskId +
                    ", videoCreateTime=" + videoCreateTime +
                    ", videoName='" + videoName + '\'' +
                    '}';
        }
    }
}
