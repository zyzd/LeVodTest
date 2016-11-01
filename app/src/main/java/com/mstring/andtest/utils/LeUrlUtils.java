package com.mstring.andtest.utils;

import android.text.TextUtils;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

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
    public static final String userid = "849894";

    /**
     * UUID:user_unique
     */
    public static final String user_unique = "p94tg27gs8";
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
    /**
     * 签名
     */
    public static final String SIGN = "sign";


    /**
     * @param video_id 视频id
     * @return 获取单个视频的详细信息
     */
    public static String getVideoGetUrl(int video_id) {
        StringBuffer sb = new StringBuffer();
        TreeMap<String, Object> map = new TreeMap<String, Object>();
        map.put("video_id", video_id);
        map.putAll(getBaseVodMaps(API_VIDEO_GET));
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
    public static String getVideoListUrl(String video_name, int status, int index, int size) {

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
        treeMap.putAll(getBaseVodMaps(API_VIDEO_LIST));
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
        treeMap.putAll(getBaseVodMaps(API_IMAGE_GET));
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
        treeMap.putAll(getBaseVodMaps(API_DATA_VIDEO_HOUR));
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
        treeMap.putAll(getBaseVodMaps(API_DATA_VIDEO_DATE));
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
        treeMap.putAll(getBaseVodMaps(API_DATA_TOTAL_DATE));
        treeMap.put("start_date", start_date);
        treeMap.put("end_date", end_date);
        treeMap.put("index", index);
        treeMap.put("size", size);

        return getLeVodUrl(treeMap);
    }

    /**
     * 视频播放首帧截图设置
     *
     * @param video_id
     * @param init_pic
     * @return 视频播放首帧截图设置的url地址
     */
    public static String getUpdateInitPicUrl(int video_id, String init_pic) {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(getBaseVodMaps(API_PLAY_UPDATE_INITPIC));
        treeMap.put("video_id", video_id);
        treeMap.put("init_pic", init_pic);
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

    /**
     * @param api 标准点播的api名
     * @return 返回标准点播的基本参数的map集合
     */
    public static Map<String, Object> getBaseVodMaps(String api) {
        TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
        treeMap.put("api", api);
        treeMap.put("format", format);
        treeMap.put("timestamp", getTimeStamp());
        treeMap.put("user_unique", user_unique);
        treeMap.put("ver", ver_vod);
        return treeMap;
    }

    /**
     * @return 当前时间（用于获取时间戳）
     */
    public static long getTimeStamp() {
        return (new Date()).getTime();
    }


    //---------------------------------------------------华丽分割线：以下是直播请求--------------------------------------------------


    /**
     * 标准直播的接口地址
     */
    public static final String BaseLeLivePath = "http://api.open.lecloud.com/live/execute";
    /**
     * 标准直播的版本号
     */
    public static final String ver_live = "4.0";

    /**
     * 创建直播活动的Method
     */
    public static final String LE_LIVE_METHOD_CREATE = "lecloud.cloudlive.activity.create";
    /**
     * 查询直播活动的Method
     */
    public static final String LE_LIVE_METHOD_SEARCH = "lecloud.cloudlive.activity.search";
    /**
     * 修改直播活动的Method
     */
    public static final String LE_LIVE_METHOD_MODIFY = "lecloud.cloudlive.activity.modify";
    /**
     * 修改直播活动封面method(直接上传一个图片，支持格式jpg、png、gif)
     */
    public static final String LE_LIVE_METHOD_MODIFY_COVERIMG = "lecloud.cloudlive.activity.modifyCoverImg";
    /**
     * 停止直播活动，停止后不能再次使用该活动进行直播。
     */
    public static final String LE_LIVE_METHOD_STOP = "lecloud.cloudlive.activity.stop";
    /**
     * 活动安全设置
     */
    public static final String LE_LIVE_METHOD_Sercurity_config = "lecloud.cloudlive.activity.sercurity.config";
    /**
     * 获取播放页地址
     */
    public static final String LE_LIVE_METHOD_PLAYERPAGE_GETURL = "lecloud.cloudlive.activity.playerpage.getUrl";
    /**
     * 获取推流token，用于调用乐视云上传SDK使用。
     */
    public static final String LE_LIVE_METHOD_GET_PUSHTOKEN = "lecloud.cloudlive.activity.getPushToken";
    /**
     * 获取直播推流地址。
     */
    public static final String LE_LIVE_METHOD_GET_PUSHURL = "lecloud.cloudlive.activity.getPushUrl";
    /**
     * 获取直播推流地址。
     */
    public static final String LE_LIVE_METHOD_GET_ACTIVITY_MACHINESTATE = "lecloud.cloudlive.activity.getActivityMachineState";
    /**
     * 用于第三方播放器获取直播活动的流信息。
     */
    public static final String LE_LIVE_METHOD_STREAMINFO_SEARCH = "lecloud.cloudlive.vrs.activity.streaminfo.search";
    /**
     * 打点录制创建任务接口:创建打点录制任务。
     */
    public static final String LE_LIVE_METHOD_CREATE_REC_TASK = "lecloud.cloudlive.rec.createRecTask";
    /**
     * 根据直播ID、任务ID等条件，查询打点录制结果。
     */
    public static final String LE_LIVE_METHOD_REC_SEARCH_RESULT = "lecloud.cloudlive.rec.searchResult";
    /**
     * 根据活动ID，查询录制视频的videoId和videoUnique，其中videoUnique组合成播放地址。
     */
    public static final String LE_LIVE_METHOD_GET_PLAY_INFO = "lecloud.cloudlive.activity.getPlayInfo";


    /**
     * @param method 标准直播的方法名
     * @return 返回标准直播的基本参数的map集合
     */
    public static TreeMap<String, String> getBaseLiveMaps(String method) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("method", method);
        treeMap.put("timestamp", getTimeStamp() + "");
        treeMap.put("userid", userid);
        treeMap.put("ver", ver_live);
        return treeMap;
    }

    /**
     * @param activityName     直播活动名称(200个字符以内)
     * @param activityCategory 活动分类，参见下面《活动分类编码表》
     * @param startTime        开始时间，从1970开始的毫秒数
     * @param endTime          结束时间，从1970开始的毫秒数
     * @param playMode         播放模式，0：实时直播 1：流畅直播。
     * @param liveNum          机位数量，范围为：1,2,3,4. 默认为1
     * @param codeRateTypes    流的码率类型，由大到小排列。取值范围：13 流畅；16 高清；19 超清； 25   1080P；99 原画。默认按最高码率播放，如果有原画则按原画播放
     * @param params           coverImgUrl     活动封面地址，如果为空，则系统会默认一张图片
     *                         description     活动描述（1024个字符以内）
     *                         needRecord      是否支持全程录制       0：否    1：是。默认为0
     *                         needTimeShift   是否支持时移          0：否    1：是。默认为0
     *                         needFullView	   是否全景观看          0：否    1：是。默认为0
     * @return 乐视创建标准直播活动的参数列表
     */
    public static Map<String, String> getLiveCreateMaps(String activityName, String activityCategory, long startTime, long endTime, int playMode, int liveNum, int[] codeRateTypes, Map<String, String> params) {
        TLog.d("zyzd", "LeUrlUtils>>getLiveCreateMaps--> ");
        Map<String, String> treeMap = getBaseLiveMaps(LE_LIVE_METHOD_CREATE);
        treeMap.put("activityName", activityName);
        treeMap.put("activityCategory", activityCategory);
        treeMap.put("startTime", startTime + "");
        treeMap.put("endTime", endTime + "");
        treeMap.put("playMode", playMode + "");
        treeMap.put("liveNum", liveNum + "");
        int length = codeRateTypes.length;
        String codeRateTypesStr = "";
        if (length > 0) {
            if (length > 1) {
                for (int i = 0; i < length - 1; i++) {
                    codeRateTypesStr = codeRateTypesStr + codeRateTypes[i] + ",";
                }
            }
            codeRateTypesStr += codeRateTypes[length - 1];
        }
        treeMap.put("codeRateTypes", codeRateTypesStr);
        if (params != null && params.size() > 0) {
            treeMap.putAll(params);
        }
        treeMap.put("sign", MD5Utils.getMd5(getSignStr(treeMap)));
//        treeMap.put("coverImgUrl", coverImgUrl);
//        treeMap.put("description", description);
//        treeMap.put("needRecord", needRecord);
//        treeMap.put("needTimeShift", needTimeShift);
//        treeMap.put("needFullView", needFullView);
        return treeMap;
    }

    /**
     * 直播活动ID activityId
     */
    public static final String LE_LIVE_PARAMS_ACTIVITY_ID = "activityId";
    /**
     * 直播活动名称 activityName
     */
    public static final String LE_LIVE_PARAMS_ACTIVITY_NAME = "activityName";
    /**
     * 活动分类 activityCategory
     */
    public static final String LE_LIVE_PARAMS_ACTIVITY_CATEGORY = "activityCategory";
    /**
     * 直播活动状态 activityStatus
     */
    public static final String LE_LIVE_PARAMS_ACTIVITYSTATUS = "activityStatus";
    /**
     * 开始时间，从1970开始的毫秒数
     */
    public static final String LE_LIVE_PARAMS_START_TIME = "startTime";
    /**
     * 结束时间，从1970开始的毫秒数
     */
    public static final String LE_LIVE_PARAMS_END_TIME = "endTime";
    /**
     * 活动封面地址 coverImgUrl
     */
    public static final String LE_LIVE_PARAMS_COVERIMG_URL = "coverImgUrl";
    /**
     * 活动描述 description
     */
    public static final String LE_LIVE_PARAMS_DESCRIPTION = "description";
    /**
     * 机位数量 liveNum
     */
    public static final String LE_LIVE_PARAMS_LIVE_NUM = "liveNum";
    /**
     * 流的码率类型 codeRateTypes
     */
    public static final String LE_LIVE_PARAMS_CODE_RATE_TYPES = "codeRateTypes";
    /**
     * 是否支持全程录制 needRecord
     */
    public static final String LE_LIVE_PARAMS_NEED_RECORD = "needRecord";
    /**
     * 是否支持时移 needTimeShift
     */
    public static final String LE_LIVE_PARAMS_NEED_TIMESHIFT = "needTimeShift";
    /**
     * 是否支持全景观看 needFullView
     */
    public static final String LE_LIVE_PARAMS_NEED_FULLVIEW = "needFullView";
    /**
     * 播放模式 playMode
     */
    public static final String LE_LIVE_PARAMS_PLAY_MODE = "playMode";
    /**
     * 从第几条数据开始查询 offSet
     */
    public static final String LE_LIVE_PARAMS_OFFSET = "offSet";
    /**
     * 一次返回多少条数据 fetchSize
     */
    public static final String LE_LIVE_PARAMS_FETCHSIZE = "fetchSize";
    /**
     * 直播ID，直播id查询参考《活动流信息查询接口》文档
     */
    public static final String LE_LIVE_PARAMS_LIVEID = "liveId";
    /**
     * 任务ID
     */
    public static final String LE_LIVE_PARAMS_TASKID = "taskId";
    /**
     * 开始行数 offset 用于打点录制查询
     */
    public static final String LE_LIVE_PARAMS_OFFSET_MIN = "offset";
    /**
     * 每页记录数
     */
    public static final String LE_LIVE_PARAMS_SIZE = "size";

    /**
     * @param params 当传递的参数为null或传递的为空map时，默认查询所有
     *               activityId	 	    LE_LIVE_PARAMS_ACTIVITY_ID       直播活动ID
     *               activityName       LE_LIVE_PARAMS_ACTIVITY_NAME     直播活动名称
     *               activityStatus	    LE_LIVE_PARAMS_ACTIVITYSTATUS   直播活动状态。0：未开始 1：已开始 3：已结束
     *               offSet	            LE_LIVE_PARAMS_OFFSET           从第几条数据开始查询，默认0
     *               fetchSize		    LE_LIVE_PARAMS_FETCHSIZE        一次返回多少条数据，默认为10，最多不能超过100条
     * @return 返回查询活动列表
     */
    public static String getLeLiveSearchUrl(TreeMap<String, String> params) {
        TreeMap<String, String> treeMap = getBaseLiveMaps(LE_LIVE_METHOD_SEARCH);
        if (params != null) {
            treeMap.putAll(params);
        }
        String url = getLeLiveUrl(treeMap);
        TLog.d("zyzd", "LeUrlUtils>>getLeLiveSearchUrl--> " + url);
        return url;
    }

    /**
     * @param activityId 直播活动ID
     * @param params     以下参数为非传参数，可视具体情况进行传入
     *                   LE_LIVE_PARAMS_ACTIVITY_NAME      直播活动名称(200个字符以内)
     *                   LE_LIVE_PARAMS_ACTIVITY_CATEGORY  活动分类
     *                   LE_LIVE_PARAMS_START_TIME         开始时间，从1970开始的毫秒数
     *                   LE_LIVE_PARAMS_END_TIME           结束时间，从1970开始的毫秒数
     *                   LE_LIVE_PARAMS_COVERIMG_URL       活动封面地址
     *                   LE_LIVE_PARAMS_DESCRIPTION        活动描述（255个字符以内）
     *                   LE_LIVE_PARAMS_LIVE_NUM           机位数量，范围为：1，2，3，4
     *                   LE_LIVE_PARAMS_CODE_RATE_TYPES    流的码率类型，逗号分隔。取值范围：13 流畅； 16 高清；19 超清； 25 1080P；99 原画
     *                   LE_LIVE_PARAMS_NEED_RECORD        是否支持全程录制 0：否 1：是
     *                   LE_LIVE_PARAMS_NEED_TIMESHIFT     是否支持时移 0：否 1：是
     *                   LE_LIVE_PARAMS_NEED_FULLVIEW      是否支持全景观看 0：否 1：是
     *                   LE_LIVE_PARAMS_PLAY_MODE          播放模式：0：实时直播；1：流畅直播
     * @return 获取修改直播活动的参数map集合（post）
     */
    public static TreeMap<String, String> getLiveModifyMaps(String activityId, TreeMap<String, String> params) {

        TreeMap<String, String> treeMap = getBaseLiveMaps(LE_LIVE_METHOD_MODIFY);

        treeMap.put(LE_LIVE_PARAMS_ACTIVITY_ID, activityId);

        if (params != null && params.size() > 0) {
            treeMap.putAll(params);
        }

        treeMap.put(SIGN, MD5Utils.getMd5(getSignStr(treeMap)));

        return treeMap;
    }

    /**
     * 修改直播活动封面。直接上传一个图片，支持格式jpg、png、gif。
     *
     * @param activityId 直播活动ID
     * @return 获取修改直播活动封面的参数map集合
     */
    public static TreeMap<String, String> getLiveModifyCoverImgMap(String activityId) {
        TreeMap<String, String> treeMap = getBaseLiveMaps(LE_LIVE_METHOD_MODIFY_COVERIMG);
        treeMap.put(LE_LIVE_PARAMS_ACTIVITY_ID, activityId);
        treeMap.put(SIGN, MD5Utils.getMd5(getSignStr(treeMap)));
        return treeMap;
    }

    /**
     * 停止直播活动，停止后不能再次使用该活动进行直播。
     *
     * @param activityId
     * @return
     */
    public static TreeMap<String, String> getLiveStopMap(String activityId) {
        TreeMap<String, String> treeMap = getBaseLiveMaps(LE_LIVE_METHOD_STOP);
        treeMap.put(LE_LIVE_PARAMS_ACTIVITY_ID, activityId);
        treeMap.put(SIGN, MD5Utils.getMd5(getSignStr(treeMap)));
        return treeMap;
    }

    /**
     * 获取播放页地址。
     *
     * @param activityId
     * @return
     */
    public static String getLivePlayerpageGetUrl(String activityId) {
        TreeMap<String, String> treeMap = getBaseLiveMaps(LE_LIVE_METHOD_PLAYERPAGE_GETURL);
        treeMap.put(LE_LIVE_PARAMS_ACTIVITY_ID, activityId);

        String url = getLeLiveUrl(treeMap);
        TLog.d("zyzd", "LeUrlUtils>>getLivePlayerpageGetUrl--> " + url);
        return url;
    }

    /**
     * 获取推流token，用于调用乐视云上传SDK使用。
     *
     * @param activityId
     * @return
     */
    public static String getLiveGetPushTokenUrl(String activityId) {
        TreeMap<String, String> treeMap = getBaseLiveMaps(LE_LIVE_METHOD_GET_PUSHTOKEN);
        treeMap.put(LE_LIVE_PARAMS_ACTIVITY_ID, activityId);
        String url = getLeLiveUrl(treeMap);
        TLog.d("zyzd", "LeUrlUtils>>getLivePlayerpageGetUrl--> " + url);
        return url;
    }

    /**
     * 获取直播推流地址。
     *
     * @param activityId
     * @return
     */
    public static String getLiveGetPushUrl(String activityId) {
        TreeMap<String, String> treeMap = getBaseLiveMaps(LE_LIVE_METHOD_GET_PUSHURL);
        treeMap.put(LE_LIVE_PARAMS_ACTIVITY_ID, activityId);
        String url = getLeLiveUrl(treeMap);
        TLog.d("zyzd", "LeUrlUtils>>getLivePlayerpageGetUrl--> " + url);
        return url;
    }

    /**
     * 查询活动下各机位的状态
     *
     * @param activityId
     * @return
     */
    public static String getLiveGetActivityMachineStateUrl(String activityId) {
        TreeMap<String, String> treeMap = getBaseLiveMaps(LE_LIVE_METHOD_GET_ACTIVITY_MACHINESTATE);
        treeMap.put(LE_LIVE_PARAMS_ACTIVITY_ID, activityId);
        String url = getLeLiveUrl(treeMap);
        TLog.d("zyzd", "LeUrlUtils>>getLivePlayerpageGetUrl--> " + url);
        return url;
    }

    /**
     * 用于第三方播放器获取直播活动的流信息。
     *
     * @param activityId
     * @return
     */
    public static String getLiveStreaminfoSearchUrl(String activityId) {
        TreeMap<String, String> treeMap = getBaseLiveMaps(LE_LIVE_METHOD_STREAMINFO_SEARCH);
        treeMap.put(LE_LIVE_PARAMS_ACTIVITY_ID, activityId);
        String url = getLeLiveUrl(treeMap);
        TLog.d("zyzd", "LeUrlUtils>>getLivePlayerpageGetUrl--> " + url);
        return url;
    }

    /**
     * 创建打点录制任务。
     * 原画码率作为输入码率，直播上的码率作为输出码率，执行转码获取打点录制任务。
     *
     * @param liveId    直播ID，直播id查询参考《活动流信息查询接口》文档
     * @param startTime 开始时间，从1970开始的毫秒数
     * @param endTime   结束时间，从1970开始的毫秒数
     * @return
     */
    public static TreeMap<String, String> getCreateRecTaskMap(String liveId, String startTime, String endTime) {
        TreeMap<String, String> treeMap = getBaseLiveMaps(LE_LIVE_METHOD_CREATE_REC_TASK);
        treeMap.put(LE_LIVE_PARAMS_LIVEID, liveId);
        treeMap.put(LE_LIVE_PARAMS_START_TIME, startTime);
        treeMap.put(LE_LIVE_PARAMS_END_TIME, endTime);
        treeMap.put(SIGN, MD5Utils.getMd5(getSignStr(treeMap)));
        return treeMap;
    }


    /**
     * 打点录制查询结果接口
     * 根据直播ID、任务ID等条件，查询打点录制结果。
     * <p>
     * 可以设置的参数为：
     * 直播ID                         liveId           LE_LIVE_PARAMS_LIVEID
     * 任务ID                         taskId           LE_LIVE_PARAMS_TASKID
     * 开始行数                        offset           LE_LIVE_PARAMS_OFFSET_MIN
     * 每页记录数                      size             LE_LIVE_PARAMS_SIZE
     * 开始时间，从1970开始的毫秒数      startTime        LE_LIVE_PARAMS_START_TIME
     * 结束时间，从1970开始的毫秒数      endTime          LE_LIVE_PARAMS_END_TIME
     *
     * @return
     */
    public static String getLiveRecSearchResultUrl(TreeMap<String, String> params) {
        TreeMap<String, String> treeMap = getBaseLiveMaps(LE_LIVE_METHOD_REC_SEARCH_RESULT);
        if (params != null) {
            treeMap.putAll(params);
        }
        String url = getLeLiveUrl(treeMap);
        TLog.d("zyzd", "LeUrlUtils>>getLivePlayerpageGetUrl--> " + url);
        return url;
    }

    /**
     * 获取录制视频信息接口
     * 功能说明： 根据活动ID，查询录制视频的videoId和videoUnique，其中videoUnique组合成播放地址。
     *
     * @param activityId
     * @return
     */
    public static String getLiveGetPlayInfo(String activityId) {
        TreeMap<String, String> treeMap = getBaseLiveMaps(LE_LIVE_METHOD_GET_PLAY_INFO);
        treeMap.put(LE_LIVE_PARAMS_ACTIVITY_ID, activityId);
        String url = getLeLiveUrl(treeMap);
        TLog.d("zyzd", "LeUrlUtils>>getLivePlayerpageGetUrl--> " + url);
        return url;
    }


    /**
     * @param map
     * @return 乐视标准直播的Get请求路径
     */
    private static String getLeLiveUrl(TreeMap<String, String> map) {
        Set<String> keys = map.keySet();
        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            TLog.d("zyzd", "LeUrlUtils>>getLeLiveUrl--> " + key);
            sb.append(key).append(STR_EQUALS).append(map.get(key)).append(STR_AND);
        }

        String signStrPre = sb.toString();
        sb.append("sign=").append(MD5Utils.getMd5(signStrPre.replaceAll("&|=", "") + secretkey));
        sb.insert(0, BaseLeLivePath).insert(BaseLeLivePath.length(), "?");
        return sb.toString();
    }


    /**
     * @param treeMap 参数集合
     * @return 返回拼接后签名原文
     */
    private static String getSignStr(Map<String, String> treeMap) {
        Set<String> keySet = treeMap.keySet();
        StringBuffer sb = new StringBuffer();
        for (String key : keySet) {
            sb.append(key).append(treeMap.get(key));
        }
        sb.append(secretkey);
        return sb.toString();
    }
}
