package com.mstring.andtest.utils;

import android.widget.Toast;

import com.mstring.andtest.ApiApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 李宗源 on 2016/11/1.
 */

public class DateUtil {

    public static final String format1 = "yyyy-MM-dd HH:mm:ss";

    public static Date transformDate(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format1);
        Date date = null;
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            Toast.makeText(ApiApplication.getContext(), "时间格式错误", Toast.LENGTH_SHORT).show();
        }
        return date;
    }
}
