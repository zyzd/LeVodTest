package com.mstring.andtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }

    @Override
    public void addListener() {
        mBtnGetVideo.setOnClickListener(this);
        mBtnVideoList.setOnClickListener(this);
        mBtnImageGet.setOnClickListener(this);
        mBtnDataVideoHour.setOnClickListener(this);
        mBtnDataVideoDate.setOnClickListener(this);
        mBtnDataTotalDate.setOnClickListener(this);
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


}
