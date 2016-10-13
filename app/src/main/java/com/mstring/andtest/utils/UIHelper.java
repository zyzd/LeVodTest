package com.mstring.andtest.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mstring.andtest.activity.MainActivity;
import com.mstring.andtest.activity.live.LeLiveCreateActivty;
import com.mstring.andtest.activity.live.LeLiveModifyActivty;
import com.mstring.andtest.activity.live.LeLiveModifyCoverImgActivty;
import com.mstring.andtest.activity.live.LeLiveSearchActivty;
import com.mstring.andtest.activity.live.LiveHomeActivity;
import com.mstring.andtest.activity.vod.DataTotalDateActivity;
import com.mstring.andtest.activity.vod.DataVideoDateActivity;
import com.mstring.andtest.activity.vod.DataVideoHourActvity;
import com.mstring.andtest.activity.vod.ImageGetActivity;
import com.mstring.andtest.activity.vod.PlayActivity;
import com.mstring.andtest.activity.vod.VideoGetActivity;
import com.mstring.andtest.activity.vod.VideoListActivity;
import com.mstring.andtest.activity.vod.VideoListShowActivity;
import com.mstring.andtest.activity.vod.VodHomeActivity;

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

    public static void openLeVodHomeActivty(Context context) {
        Intent intent = new Intent(context, VodHomeActivity.class);
        context.startActivity(intent);
    }

    public static void openLeLiveHomeActivty(Context context) {
        Intent intent = new Intent(context, LiveHomeActivity.class);
        context.startActivity(intent);
    }

    public static void openLeLiveCreateActivty(Context context) {
        Intent intent = new Intent(context, LeLiveCreateActivty.class);
        context.startActivity(intent);
    }

    public static void openLeLiveSearchActivty(Context context) {
        Intent intent = new Intent(context, LeLiveSearchActivty.class);
        context.startActivity(intent);
    }

    public static void openLeLiveModifyActiMvty(Context context) {
        Intent intent = new Intent(context, LeLiveModifyActivty.class);
        context.startActivity(intent);
    }
    public static void openLeLiveModifyCoverImgActivty(Context context) {
        Intent intent = new Intent(context, LeLiveModifyCoverImgActivty.class);
        context.startActivity(intent);
    }
}
