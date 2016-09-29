package com.mstring.andtest.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseNetActivity;
import com.mstring.andtest.bean.LeResultBean;
import com.mstring.andtest.bean.LeVideoGetBean;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.TLog;
import com.mstring.andtest.utils.VolleyHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by 李宗源 on 2016/9/28.
 */

public class VideoListActivity extends BaseNetActivity<ArrayList<LeVideoGetBean>> {

    private Button mBtnStartRequest;
    private TextView mTvResult;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_get;
    }

    @Override
    protected void initView() {
        findViewById(R.id.til_video_id).setVisibility(View.GONE);
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
        String url = LeUrlUtils.getVideoListUrl(1, 5, 10, null);
        TLog.d("zyzd", "VideoListActivity>>url--> " + url);
        VolleyHelper.stringRequestByGet(url,this,this);
    }

    @Override
    protected void parseData(ArrayList<LeVideoGetBean> data) {
        mTvResult.setText(data.toString());
    }



    @Override
    protected Type getType() {
        return new TypeToken<LeResultBean<ArrayList<LeVideoGetBean>>>(){}.getType();
    }


}
