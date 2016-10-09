package com.mstring.andtest.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mstring.andtest.activity.DataTotalDateActivity;
import com.mstring.andtest.activity.DataVideoDateActivity;
import com.mstring.andtest.activity.DataVideoHourActvity;
import com.mstring.andtest.activity.ImageGetActivity;
import com.mstring.andtest.activity.PlayActivity;
import com.mstring.andtest.activity.VideoGetActivity;
import com.mstring.andtest.activity.VideoListActivity;
import com.mstring.andtest.activity.VideoListShowActivity;

/**
 * Created by 李宗源 on 2016/9/28.
 */

public class UIHelper {
    public static void openVideoGetAcitivity(Context context) {
        Intent intent = new Intent(context, VideoGetActivity.class);
        context.startActivity(intent);
    }

    public static void openVideoListAcitivity(Context context) {
        Intent intent = new Intent(context, VideoListActivity.class);
        context.startActivity(intent);
    }

    public static void openImageGetAcitivity(Context context) {
        Intent intent = new Intent(context, ImageGetActivity.class);
        context.startActivity(intent);
    }

    public static void openDataVideoHourActvity(Context context) {
        Intent intent = new Intent(context, DataVideoHourActvity.class);
        context.startActivity(intent);
    }

    public static void openDataVideoDateActvity(Context context) {
        Intent intent = new Intent(context, DataVideoDateActivity.class);
        context.startActivity(intent);
    }
    public static void openDataTotalDateActvity(Context context) {
        Intent intent = new Intent(context, DataTotalDateActivity.class);
        context.startActivity(intent);
    }

    public static void openPlayActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PlayActivity.class);
        intent.putExtra(PlayActivity.DATA, bundle);
        context.startActivity(intent);
    }

    public static void openVideoListShowActivity(Context context) {
        Intent intent = new Intent(context, VideoListShowActivity.class);
        context.startActivity(intent);
    }
}
