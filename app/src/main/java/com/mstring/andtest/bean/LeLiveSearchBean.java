package com.mstring.andtest.bean;

import java.io.Serializable;

import static android.R.attr.description;

/**
 * Created by 李宗源 on 2016/10/11.
 */

public class LeLiveSearchBean implements Serializable {

    private String activityId;          //活动ID
    private String activityName;        //活动名称
    private String activityCategory;    //活动分类
    private int activityStatus;         //活动状态。0：未开始 1：已开始  3：已结束
    private String startTime;           //开始时间。从1970开始的毫秒数              "startTime": 1475907720000
    private String endTime;             //结束时间。从1970开始的毫秒数              "endTime": 1476080520000,
    private String createTime;          //活动创建时间。从1970开始的毫秒数
    private String coverImgUrl;         //活动封面地址
    private String description;         //活动描述
    private int playMode;               //播放模式。0：实时直播；1：流畅直播
    private int liveNum;                //机位数量。最多4个机位
    private int needRecord;             //是否支持全程录制。0：否 1：是
    private int needTimeShift;          //是否支持时移。0：否 1：是
    private int needFullView;           //是否支持全景观看。0：否 1：是
    private int neededPushAuth;         //是否启用推流鉴权。0：否 1：是
    private int pushUrlValidTime;       //推流地址有效时长。单位s，启用推流鉴权时有效
    private int needIpWhiteList;        //是否启用IP推流白名单。0：否 1：是
    private String pushIpWhiteList;     //推流IP白名单。启用IP推流白名单时有效

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityCategory() {
        return activityCategory;
    }

    public void setActivityCategory(String activityCategory) {
        this.activityCategory = activityCategory;
    }

    public int getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(int activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPlayMode() {
        return playMode;
    }

    public void setPlayMode(int playMode) {
        this.playMode = playMode;
    }

    public int getLiveNum() {
        return liveNum;
    }

    public void setLiveNum(int liveNum) {
        this.liveNum = liveNum;
    }

    public int getNeedRecord() {
        return needRecord;
    }

    public void setNeedRecord(int needRecord) {
        this.needRecord = needRecord;
    }

    public int getNeedTimeShift() {
        return needTimeShift;
    }

    public void setNeedTimeShift(int needTimeShift) {
        this.needTimeShift = needTimeShift;
    }

    public int getNeedFullView() {
        return needFullView;
    }

    public void setNeedFullView(int needFullView) {
        this.needFullView = needFullView;
    }

    public int getNeededPushAuth() {
        return neededPushAuth;
    }

    public void setNeededPushAuth(int neededPushAuth) {
        this.neededPushAuth = neededPushAuth;
    }

    public int getPushUrlValidTime() {
        return pushUrlValidTime;
    }

    public void setPushUrlValidTime(int pushUrlValidTime) {
        this.pushUrlValidTime = pushUrlValidTime;
    }

    public int getNeedIpWhiteList() {
        return needIpWhiteList;
    }

    public void setNeedIpWhiteList(int needIpWhiteList) {
        this.needIpWhiteList = needIpWhiteList;
    }

    public String getPushIpWhiteList() {
        return pushIpWhiteList;
    }

    public void setPushIpWhiteList(String pushIpWhiteList) {
        this.pushIpWhiteList = pushIpWhiteList;
    }

    @Override
    public String toString() {
        return "LeLiveSearchBean{" +
                "activityId='" + activityId + '\'' +
                ", activityName='" + activityName + '\'' +
                ", activityCategory='" + activityCategory + '\'' +
                ", activityStatus=" + activityStatus +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", coverImgUrl='" + coverImgUrl + '\'' +
                ", description='" + description + '\'' +
                ", playMode=" + playMode +
                ", liveNum=" + liveNum +
                ", needRecord=" + needRecord +
                ", needTimeShift=" + needTimeShift +
                ", needFullView=" + needFullView +
                ", neededPushAuth=" + neededPushAuth +
                ", pushUrlValidTime=" + pushUrlValidTime +
                ", needIpWhiteList=" + needIpWhiteList +
                ", pushIpWhiteList='" + pushIpWhiteList + '\'' +
                '}';
    }
}
