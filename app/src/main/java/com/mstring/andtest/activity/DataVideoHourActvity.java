package com.mstring.andtest.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseNetActivity;
import com.mstring.andtest.bean.LeDataVideoHourBean;
import com.mstring.andtest.bean.LeResultBean;
import com.mstring.andtest.bean.LeVideoGetBean;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.TLog;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 获取以小时为单位的视频数据
 * Created by 李宗源 on 2016/9/29.
 */

public class DataVideoHourActvity extends BaseNetActivity <ArrayList<LeDataVideoHourBean>>{

    private EditText mEtVideoId;
    private EditText mEtDate;
    private EditText mEtHour;
    private Button mBtnStartRequest;
    private TextView mTvResult;

    @Override
    protected void initView() {
        mEtVideoId = (EditText) findViewById(R.id.et_video_id);
        mEtDate = (EditText) findViewById(R.id.et_date);
        mEtHour = (EditText) findViewById(R.id.et_hour);
        mBtnStartRequest = (Button) findViewById(R.id.btn_start_request);
        mTvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    protected void addListener() {
        mBtnStartRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_request:
                startRequest();
                break;
        }
    }

    private void startRequest() {
        String videoId = mEtVideoId.getText().toString().trim();
        String date = mEtDate.getText().toString().trim();
        String hour = mEtHour.getText().toString().trim();

        String url = LeUrlUtils.getLeDataVideoHourUrl(date, Integer.parseInt(hour), 1, 10, Integer.parseInt(videoId));
        requestData(url);
    }

    @Override
    protected void parseData(ArrayList<LeDataVideoHourBean> data) {
        TLog.d("zyzd", "DataVideoHourActvity>>parseData--> " + data);
        mTvResult.setText(data.toString());
    }

    @Override
    protected Type getType() {
        return new TypeToken<LeResultBean<ArrayList<LeDataVideoHourBean>>>(){}.getType();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_video_hour;
    }
}
