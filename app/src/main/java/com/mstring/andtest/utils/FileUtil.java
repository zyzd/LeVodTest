package com.mstring.andtest.utils;

/**
 * Created by 李宗源 on 2016/8/13.
 */
public class FileUtil {
    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return
     */
    public static String getFileFormat(String fileName) {
        if (StringUtils.isEmpty(fileName))
            return "";

        int point = fileName.lastIndexOf('.');
        return fileName.substring(point + 1);
    }
}
