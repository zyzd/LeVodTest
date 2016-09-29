package com.mstring.andtest.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseNetActivity;
import com.mstring.andtest.bean.LeDataVideoDateBean;
import com.mstring.andtest.bean.LeResultBean;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.TLog;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by 李宗源 on 2016/9/29.
 */

public class DataVideoDateActivity extends BaseNetActivity<ArrayList<LeDataVideoDateBean>> {

    private EditText mEtVideoId;
    private EditText mEtStartDate;
    private EditText mEtEndDate;
    private Button mBtnStartRequest;
    private TextView mTvResult;

    @Override
    protected void initView() {
        mEtVideoId = (EditText) findViewById(R.id.et_video_id);
        mEtStartDate = (EditText) findViewById(R.id.et_start_date);
        mEtEndDate = (EditText) findViewById(R.id.et_end_date);
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
        String startDate = mEtStartDate.getText().toString().trim();
        String endDate = mEtEndDate.getText().toString().trim();

        String url = LeUrlUtils.getLeDataVideoDateUrl(Integer.parseInt(videoId),startDate, endDate, 1, 10);
        requestData(url);
    }


    @Override
    protected void parseData(ArrayList<LeDataVideoDateBean> data) {
        TLog.d("zyzd", "DataVideoDateActvity>>parseData--> " + data);
        mTvResult.setText(data.toString());
    }

    @Override
    protected Type getType() {
        return new TypeToken<LeResultBean<ArrayList<LeDataVideoDateBean>>>(){}.getType();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_video_date;
    }
}
