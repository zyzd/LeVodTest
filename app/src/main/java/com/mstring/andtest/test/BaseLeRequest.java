package com.mstring.andtest.test;

import java.util.Date;
import java.util.TreeMap;

/**
 * Created by 李宗源 on 2016/10/16.
 */

public class BaseLeRequest {
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
     * & 连接符
     */
    public static final String STR_AND = "&";
    /**
     * = 连接符
     */
    public static final String STR_EQUALS = "=";
    /**
     * 签名
     */
    public static final String SIGN = "sign";

    public class BaseLeBuidler {
        TreeMap<String, String> treeMap;
        public BaseLeBuidler(){
            treeMap = new TreeMap<>();
        }
        TreeMap<String, String> builder(){
            return treeMap;
        }
    }

    /**
     * @return 当前时间（用于获取时间戳）
     */
    public static long getTimeStamp() {
        return (new Date()).getTime();
    }
}
