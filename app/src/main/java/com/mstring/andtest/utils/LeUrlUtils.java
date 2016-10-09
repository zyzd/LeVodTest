package com.mstring.andtest.utils;

import android.text.TextUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by 李宗源 on 2016/9/28.
 */

public class LeUrlUtils {

    /**
     * 点播的接口地址
     */
    public static final String BaseLeVodPath = "http://api.letvcloud.com/open.php";
    /**
     * & 连接符
     */
    public static final String STR_AND = "&";
    /**
     * = 连接符
     */
    public static final String STR_EQUALS = "=";
    /**
     * 用户密钥
     */
    public static final String secretkey = "d59594507fd5d3a581855eaa5c60f46d";
    /**
     * 用户id
     */
    public static final String userId = "849894";

    /**
     * UUID:user_unique
     */
    public static final String user_unique = "p94tg27gs8";
    /**
     * 请求结果以json格式返回
     */
    public static final String format = "json";

    /**
     * ver 版本
     */
    public static final String ver = "2.0";
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

    /**
     * @param video_id 视频id
     * @return 获取单个视频的详细信息
     */
    public static String getVideoGetUrl(int video_id) {
        StringBuffer sb = new StringBuffer();
        TreeMap<String, Object> map = new TreeMap<String, Object>();
        map.put("video_id", video_id);
        map.putAll(getmBaseMaps(API_VIDEO_GET));
        return getLeVodUrl(map);
    }

    /**
     * @param index      开始页索引，起始值为1
     * @param size       分页大小，默认值为10，最大值为100
     * @param status     状态码
     *                   0      表示全部；
     *                   10     表示可以正常播放；
     *                   20     表示转码失败；
     *                   21     表示审核失败；
     *                   22     表示片源错误；
     *                   23     表示发布失败；
     *                   24     表示上传失败；
     *                   30     表示正在处理过程中；
     *                   31     表示正在审核过程中；
     *                   32     表示无视频源；
     *                   33     表示正在上传初始化；
     *                   34     表示正在上传过程中；
     *                   40     表示停用
     *                   默认值为0
     * @param video_name 根据视频名称模糊搜，可以为空，默认为查询所有
     * @return 获取视频列表信息
     */
    public static String getVideoListUrl(String video_name, int status ,int index, int size) {

        if (index < 1) {
            TLog.e("zyzd", "getVideoListUrl: index起始值为1");
            return "";
        }

        if (size < 1 || size > 100) {//默认值为10
            size = 10;
        }

        switch (status) {
            case 0:
            case 10:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 40:
                break;
            default:
                status = 0;
        }

        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(getmBaseMaps(API_VIDEO_LIST));
        treeMap.put("index", index);
        treeMap.put("size", size);
        treeMap.put("status", status);
        if (!TextUtils.isEmpty(video_name)) {
            treeMap.put("video_name", video_name);
        }
        return getLeVodUrl(treeMap);
    }

    /**
     * @param video_id 视频ID
     * @param size     截图尺寸，每种尺寸各有8张图。
     *                 有以下尺寸供选择：100_100、200_200、300_300、120_90、
     *                 128_96、132_99、160_120、200_150、400_300、160_90、
     *                 320_180、640_360、90_120、120_160、150_200、300_400
     * @return
     */
    public static String getLeImageGetUrl(int video_id, String size) {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(getmBaseMaps(API_IMAGE_GET));
        treeMap.put("video_id", video_id);
        treeMap.put("size", size);
        return getLeVodUrl(treeMap);
    }

    /**
     * 视频小时数据
     *
     * @param date     日期       格式为：yyyy-mm-dd
     * @param hour     小时       0-23之间，当填写非0-23之间的数值，则不上传hour参数，会查询所有小时
     * @param index    视频ID     必填参数。页索引，不可为<1的数
     * @param size     分页大小    默认值为10，最大值为100
     * @param video_id 视频ID     不需要是可以填写小于0的数，如-1
     * @return 获取以小时为单位的视频数据
     */
    public static String getLeDataVideoHourUrl(String date, int hour, int index, int size, int video_id) {

        if (date == null) {
            TLog.e("zyzd", "getLeDataVideoHourUrl: 时间不可以为空！");
            return "";
        }

        if (!date.matches(StringUtils.REGEX_DATE)) {
            TLog.e("zyzd", "getLeDataVideoHourUrl: 日期格式错误！");
            return "";
        }

        if (index < 1) {
            TLog.e("zyzd", "getLeDataVideoHourUrl: index起始值为1");
            return "";
        }
        if (size < 1 || size > 100) {
            size = 10;//默认值为10
        }

        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(getmBaseMaps(API_DATA_VIDEO_HOUR));
        treeMap.put("date", date);
        if (hour >= 0 || hour <= 23) {//小时错误则视为不作为请求参数
            treeMap.put("hour", hour);
        }
        treeMap.put("index", index);
        treeMap.put("size", size);
        if (video_id > 0) {
            treeMap.put("video_id", video_id);
        }
        return getLeVodUrl(treeMap);
    }

    /**
     * 获取以天为单位的视频数据
     *
     * @param video_id   视频ID，该参数为负数时将返回所有视频的数据（原接口是不输入该参数将返回所有视频的数据）
     * @param start_date 开始日期，格式为：yyyy-mm-dd
     * @param end_date   结束日期，格式为：yyyy-mm-dd
     * @param index      开始页索引，默认值为1
     * @param size       分页大小，默认值为10，最大值为100
     * @return
     */
    public static String getLeDataVideoDateUrl(int video_id, String start_date, String end_date, int index, int size) {

        if (index < 1) {
            TLog.e("zyzd", "getLeDataVideoHourUrl: index起始值为1");
            return "";
        }

        if (!start_date.matches(StringUtils.REGEX_DATE) || !end_date.matches(StringUtils.REGEX_DATE)) {
            TLog.e("zyzd", "getLeDataVideoDateUrl: 日期格式错误！");
            return "";
        }

        if (size < 1 || size > 100) {
            size = 10;//默认值为10
        }

        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(getmBaseMaps(API_DATA_VIDEO_DATE));
        treeMap.put("start_date", start_date);
        treeMap.put("end_date", end_date);
        treeMap.put("index", index);
        treeMap.put("size", size);
        if (video_id > 0) {
            treeMap.put("video_id", video_id);
        }

        return getLeVodUrl(treeMap);
    }

    /**
     * 获取以天为单位的所有数据
     *
     * @param start_date 开始日期，格式为：yyyy-mm-dd
     * @param end_date   结束日期，格式为：yyyy-mm-dd
     * @param index      开始页索引，默认值为1
     * @param size       分页大小，默认值为10，最大值为100
     * @return
     */
    public static String getLeDataTotalDateUrl(String start_date, String end_date, int index, int size) {

        if (index < 1) {
            TLog.e("zyzd", "getLeDataVideoHourUrl: index起始值为1");
            return "";
        }

        if (!start_date.matches(StringUtils.REGEX_DATE) || !end_date.matches(StringUtils.REGEX_DATE)) {
            TLog.e("zyzd", "getLeDataVideoDateUrl: 日期格式错误！");
            return "";
        }

        if (size < 1 || size > 100)
            size = 10;//默认值为10

        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(getmBaseMaps(API_DATA_TOTAL_DATE));
        treeMap.put("start_date", start_date);
        treeMap.put("end_date", end_date);
        treeMap.put("index", index);
        treeMap.put("size", size);

        return getLeVodUrl(treeMap);
    }

    /**
     * 视频播放首帧截图设置
     * @param video_id
     * @param init_pic
     * @return 视频播放首帧截图设置的url地址
     */
    public static String getUpdateInitPicUrl(int video_id,String init_pic){
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(getmBaseMaps(API_PLAY_UPDATE_INITPIC));
        treeMap.put("video_id",video_id);
        treeMap.put("init_pic",init_pic);
        return getLeVodUrl(treeMap);
    }


    /**
     * 返回乐视点播的Url(需要传入传入已经添加完了除secretkey的其他键值对)
     * 在该方法中计算了各接口的sign(签名),并且拼接了乐视点播的接口地址，获取最终实现具体功能的乐视点播url
     *
     * @param map 包含各种具体接口除基本路径和签名外的请求参数键值对的map对象
     * @return 实现具体功能的乐视点播url
     */
    private static String getLeVodUrl(TreeMap<String, Object> map) {
        Set<String> keys = map.keySet();
        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            TLog.d("zyzd", "LeUrlUtils>>getLeVodUrl--> " + key);
            sb.append(key).append(STR_EQUALS).append(map.get(key)).append(STR_AND);
        }

        String signStrPre = sb.toString();
        sb.append("sign=").append(MD5Utils.getMd5(signStrPre.replaceAll("&|=", "") + secretkey));
        sb.insert(0, BaseLeVodPath).insert(BaseLeVodPath.length(), "?");
        return sb.toString();
    }

    public static Map<String, Object> getmBaseMaps(String api) {
        TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
        treeMap.put("api", api);
        treeMap.put("format", format);
        treeMap.put("timestamp", getTimeStamp());
        treeMap.put("user_unique", user_unique);
        treeMap.put("ver", ver);
        return treeMap;
    }

    /**
     * @return 当前时间（用于获取时间戳）
     */
    public static long getTimeStamp() {
        return (new Date()).getTime();
    }
}
