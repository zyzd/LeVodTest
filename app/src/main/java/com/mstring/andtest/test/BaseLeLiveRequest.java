package com.mstring.andtest.test;


/**
 * Created by 李宗源 on 2016/10/16.
 */

public class BaseLeLiveRequest extends BaseLeRequest {
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

    public class BaseLeLiveBuilder extends BaseLeBuidler {

        public BaseLeLiveBuilder() {
            super();
            treeMap.put("timestamp", getTimeStamp() + "");
            treeMap.put("userid", userid);
            treeMap.put("ver", ver_live);
        }
    }
}
