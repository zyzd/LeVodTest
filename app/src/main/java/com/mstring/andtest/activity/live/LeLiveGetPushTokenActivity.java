package com.mstring.andtest.activity.live;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseLeLiveNetActivity;
import com.mstring.andtest.bean.LeLiveGetPushTokenBean;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.VolleyHelper;

import java.lang.reflect.Type;

/**
 * Created by 李宗源 on 2016/10/31.
 */

public class LeLiveGetPushTokenActivity extends BaseLeLiveNetActivity<LeLiveGetPushTokenBean> {
    public EditText mEtActivityId;
    public Button mBtnStartRequest;
    public TextView mTvResult;

    @Override
    protected void initView() {
        super.initView();
        mEtActivityId = (EditText) findViewById(R.id.et_activityId);
        mBtnStartRequest = (Button) findViewById(R.id.btn_start_request);
        mTvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    protected void addListener() {
        super.addListener();
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

        mTvResult.setText("");//先清空显示的数据
        String activityId = mEtActivityId.getText().toString().trim();

        if (TextUtils.isEmpty(activityId)) {
            Toast.makeText(getApplicationContext(), "直播活动id不可以为空!", Toast.LENGTH_SHORT).show();
        }

        String url = LeUrlUtils.getLiveGetPushTokenUrl(activityId);
        VolleyHelper.stringRequestByGet(url, this, this);
    }

    @Override
    protected void parseData(LeLiveGetPushTokenBean data) {
        mTvResult.setText(data.getToken());
    }

    @Override
    protected Type getType() {
        return new TypeToken<LeLiveGetPushTokenBean>() {
        }.getType();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_le_live_get_pushtoken;
    }
}
