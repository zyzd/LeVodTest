package com.mstring.andtest.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lecloud.sdk.constant.PlayerParams;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseNetActivity;
import com.mstring.andtest.bean.LeDataTotalDateBean;
import com.mstring.andtest.bean.LeDataVideoDateBean;
import com.mstring.andtest.bean.LeResultBean;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.TLog;
import com.mstring.andtest.utils.UIHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 获取以天为单位的所有数据
 * Created by 李宗源 on 2016/9/29.
 */

public class DataTotalDateActivity extends BaseNetActivity<ArrayList<LeDataTotalDateBean>> {

    private EditText mEtStartDate;
    private EditText mEtEndDate;
    private Button mBtnStartRequest;
    private TextView mTvResult;

    @Override
    protected void initView() {
        View view = findViewById(R.id.til_video_id);
        view.setVisibility(View.GONE);
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
        switch (v.getId()) {
            case R.id.btn_start_request:
                startRequest();
                break;
        }
    }

    private void startRequest() {
        String startDate = mEtStartDate.getText().toString().trim();
        String endDate = mEtEndDate.getText().toString().trim();

        String url = LeUrlUtils.getLeDataTotalDateUrl(startDate, endDate, 1, 10);
        requestData(url);
    }


    @Override
    protected void parseData(ArrayList<LeDataTotalDateBean> data) {
        TLog.d("zyzd", "DataTotalDateActivity>>parseData--> " + data);
        mTvResult.setText(data.toString());
    }


    @Override
    protected Type getType() {
        return new TypeToken<LeResultBean<ArrayList<LeDataTotalDateBean>>>() {
        }.getType();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_video_date;
    }
}
