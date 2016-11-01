package com.mstring.andtest.activity.live;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseActivity;
import com.mstring.andtest.base.BaseLeLiveNetActivity;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.TLog;
import com.mstring.andtest.utils.VolleyHelper;

import java.lang.reflect.Type;
import java.util.TreeMap;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by 李宗源 on 2016/10/31.
 */

public class LeLiveStopActivity extends BaseLeLiveNetActivity {
    public EditText mEtActivityId;
    public Button mBtnStartRequest;
    public TextView mTvResult;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_le_live_stop;
    }

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
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_start_request:
                startRequest();
                break;
        }
    }

    private void startRequest() {
        mTvResult.setText("");
        String activityId = mEtActivityId.getText().toString().trim();

        TreeMap<String, String> stopMap = LeUrlUtils.getLiveStopMap(activityId);

        VolleyHelper.stringRequestByPost(LeUrlUtils.BaseLeLivePath, stopMap, this, this);
    }


    @Override
    protected void onSuccess(String response) {
//        super.onSuccess(response);
        TLog.d("zyzd", "LeLiveStopActivity>>onSuccess--> " + "success");
        mTvResult.setText("success");
    }

    @Override
    protected void parseData(Object data) {

    }

    @Override
    protected Type getType() {
        return null;
    }
}
