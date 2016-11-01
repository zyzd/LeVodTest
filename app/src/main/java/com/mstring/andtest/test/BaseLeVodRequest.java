package com.mstring.andtest.test;

/**
 * Created by 李宗源 on 2016/10/16.
 */

public class BaseLeVodRequest extends BaseLeRequest {
    /**
     * 点播的接口地址
     */
    public static final String BaseLeVodPath = "http://api.letvcloud.com/open.php";
    /**
     * 请求结果以json格式返回
     */
    public static final String format = "json";
    /**
     * ver_vod 点播版本
     */
    public static final String ver_vod = "2.0";
    /**
     * 获取单个视频的详细信息的api
     */
    public static final String API_VIDEO_GET = "video.get";

    /**
     * 获取视频列表的api
     */
    public static final String API_VIDEO_LIST = "video.list";
    /**
     * 获取视频截图
     */
    public static final String API_IMAGE_GET = "image.get";
    /**
     * 获取以小时为单位的视频数据
     */
    public static final String API_DATA_VIDEO_HOUR = "data.video.hour";
    /**
     * 获取以天为单位的视频数据
     */
    public static final String API_DATA_VIDEO_DATE = "data.video.date";
    /**
     * 获取以天为单位的视频数据
     */
    public static final String API_DATA_TOTAL_DATE = "data.total.date";
    /**
     * 视频播放首帧截图设置
     */
    public static final String API_PLAY_UPDATE_INITPIC = "play.update.initpic";
}
