package com.mstring.andtest.bean;

import java.io.Serializable;

/**
 * Created by 李宗源 on 2016/10/31.
 * 直播机台bean
 */

public class LeLiveLiveBean implements Serializable {
    private String bakPullUrl;
    private String machine;//机位位置。1-4机位
    private String pullUrl;
    private String pushUrl;//推流地址
    private String status;//状态。0：无信号 1：有信号
    private String streamId;
    private String streamStatus;

    public String getBakPullUrl() {
        return bakPullUrl;
    }

    public void setBakPullUrl(String bakPullUrl) {
        this.bakPullUrl = bakPullUrl;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getPullUrl() {
        return pullUrl;
    }

    public void setPullUrl(String pullUrl) {
        this.pullUrl = pullUrl;
    }

    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public String getStreamStatus() {
        return streamStatus;
    }

    public void setStreamStatus(String streamStatus) {
        this.streamStatus = streamStatus;
    }

    @Override
    public String toString() {
        return "LeLiveLiveBean{" +
                "bakPullUrl='" + bakPullUrl + '\'' +
                ", machine='" + machine + '\'' +
                ", pullUrl='" + pullUrl + '\'' +
                ", pushUrl='" + pushUrl + '\'' +
                ", status='" + status + '\'' +
                ", streamId='" + streamId + '\'' +
                ", streamStatus='" + streamStatus + '\'' +
                '}';
    }
}
