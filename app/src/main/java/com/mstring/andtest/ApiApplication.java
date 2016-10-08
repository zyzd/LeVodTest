package com.mstring.andtest;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.app.ActivityManager.RunningAppProcessInfo;

import com.lecloud.sdk.config.LeCloudPlayerConfig;
import com.lecloud.sdk.listener.OnInitCmfListener;
import com.mstring.andtest.handler.CrashHandler;

import java.util.List;

import static com.letvcloud.cmf.utils.DeviceUtils.getProcessName;

/**
 * Created by 李宗源 on 2016/9/28.
 */

public class ApiApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = getProcessName(this, android.os.Process.myPid());
        //设置域名LeCloudPlayerConfig.HOST_DEFAULT代表国内版
        //LeCloudPlayerConfig.HOST_US代表国际版
        //LeCloudPlayerConfig.HOST_GUOGUANG代表国广版
        //国内用户都使用默认的LeCloudPlayerConfig.HOST_DEFAULT
        int host = LeCloudPlayerConfig.HOST_DEFAULT;
        if (getApplicationInfo().packageName.equals(processName)) {
            //CrashHandler是一个抓取崩溃log的工具类（可选）
            CrashHandler.getInstance(this);
            try {
                LeCloudPlayerConfig.setHostType(host);
                LeCloudPlayerConfig.init(getApplicationContext());
                LeCloudPlayerConfig.setmInitCmfListener(new OnInitCmfListener() {
                    @Override
                    public void onCdeStartSuccess() {
                        //cde启动成功,可以开始播放
                    }

                    @Override
                    public void onCdeStartFail() { //cde启动失败,不能正常播放;
                        //如果使用remote版本则可能是remote下载失败;
                        //如果使用普通版本,则可能是so文件加载失败导致
                    }

                    @Override
                    public void onCmfCoreInitSuccess() {
                        //不包含cde的播放框架需要处理
                    }

                    @Override
                    public void onCmfCoreInitFail() {
                        //不包含cde的播放框架需要处理
                    }

                    @Override
                    public void onCmfDisconnected() {
                        //cde服务断开,会导致播放失败,重启一次服务
                        try {
                            LeCloudPlayerConfig.init(getApplicationContext());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        mContext = this;

    }

    //获取当前进程名字
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager)
                cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null) {
            for (RunningAppProcessInfo procInfo : runningApps) {
                if (procInfo.pid == pid) {
                    return procInfo.processName;
                }
            }
        }
        return null;
    }


    public static Context getContext() {
        return mContext;
    }
}
