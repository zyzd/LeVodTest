package com.mstring.andtest;

import android.app.Application;
import android.content.Context;

/**
 * Created by 李宗源 on 2016/9/28.
 */

public class ApiApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
