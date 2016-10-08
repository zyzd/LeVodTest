package com.mstring.andtest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lecloud.sdk.constant.PlayerParams;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseActivity;
import com.mstring.andtest.utils.UIHelper;

public class MainActivity extends BaseActivity {

    private Button mBtnGetVideo;
    private Button mBtnVideoList;
    private Button mBtnImageGet;
    private Button mBtnDataVideoHour;
    private Button mBtnDataVideoDate;
    private Button mBtnDataTotalDate;
    private Button mBtnPlayView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mBtnGetVideo = (Button) findViewById(R.id.btn_video_get);
        mBtnVideoList = (Button) findViewById(R.id.btn_video_list);
        mBtnImageGet = (Button) findViewById(R.id.btn_image_get);
        mBtnDataVideoHour = (Button) findViewById(R.id.btn_data_video_hour);
        mBtnDataVideoDate = (Button) findViewById(R.id.btn_data_video_date);
        mBtnDataTotalDate = (Button) findViewById(R.id.btn_data_total_date);
        mBtnPlayView = (Button) findViewById(R.id.btn_play_view);
    }

    @Override
    public void addListener() {
        mBtnGetVideo.setOnClickListener(this);
        mBtnVideoList.setOnClickListener(this);
        mBtnImageGet.setOnClickListener(this);
        mBtnDataVideoHour.setOnClickListener(this);
        mBtnDataVideoDate.setOnClickListener(this);
        mBtnDataTotalDate.setOnClickListener(this);
        mBtnPlayView.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_video_get:
                requestVideoInfo();
                break;
            case R.id.btn_video_list:
                requestVideoList();
                break;
            case R.id.btn_image_get:
                requestImageGet();
                break;
            case R.id.btn_data_video_hour:
                requestDataVideoHour();
                break;
            case R.id.btn_data_video_date:
                requestDataVideoDate();
                break;
            case R.id.btn_data_total_date:
                requestDataTotalDate();
                break;
            case R.id.btn_play_view:
                openPlayView();
                break;
        }
    }

    private void requestVideoInfo() {
        UIHelper.openVideoGetAcitivity(this);
    }

    private void requestVideoList() {
        UIHelper.openVideoListAcitivity(this);
    }

    private void requestImageGet() {
        UIHelper.openImageGetAcitivity(this);
    }

    private void requestDataVideoHour() {
        UIHelper.openDataVideoHourActvity(this);
    }

    private void requestDataVideoDate() {
        UIHelper.openDataVideoDateActvity(this);
    }

    private void requestDataTotalDate() {
        UIHelper.openDataTotalDateActvity(this);
    }

    private void openPlayView() {
        UIHelper.openPlayActivity(this,getBundle());
    }

    /**
     * 乐视云点播
     */
    private Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(PlayerParams.KEY_PLAY_MODE, PlayerParams.VALUE_PLAYER_VOD);
        if(true){
            bundle.putString(PlayerParams.KEY_PLAY_UUID, "p94tg27gs8");
            bundle.putString(PlayerParams.KEY_PLAY_VUID, "d0386838c5");
            bundle.putString(PlayerParams.KEY_PLAY_CHECK_CODE, "");
            bundle.putString(PlayerParams.KEY_PLAY_PAYNAME, "0");
            bundle.putString(PlayerParams.KEY_PLAY_USERKEY, "151398");
//			bundle.putString(PlayerParams.KEY_PLAY_BUSINESSLINE, "101");
            bundle.putString(PlayerParams.KEY_PLAY_PU, "0");
        }else{
            // Url可以是在线视频，也可以是本地视频
            // String playPath = "/sdcard/demo.mp4"
            bundle.putString("path", "http://cache.utovr.com/201601131107187320.mp4");
        }
        bundle.putBoolean("pano", false);
        bundle.putBoolean("hasSkin",true);
        return bundle;
    }

}
