package com.mstring.andtest.activity.live;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseLeLiveNetActivity;
import com.mstring.andtest.bean.LeLiveSearchBean;
import com.mstring.andtest.utils.LeUrlUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.TreeMap;

public class LeLiveSearchActivty extends BaseLeLiveNetActivity<ArrayList<LeLiveSearchBean>> {

    private EditText mEtActivityId;
    private EditText mEtActivityName;
    private EditText mEtActivityStatus;
    private EditText mEtOffSet;
    private EditText mEtFetchSize;
    private Button mBtnStartRequest;
    private TextView mTvResult;

    @Override
    protected void initView() {
        mEtActivityId = (EditText) findViewById(R.id.et_activityId);
        mEtActivityName = (EditText) findViewById(R.id.et_activityName);
        mEtActivityStatus = (EditText) findViewById(R.id.et_activityStatus);
        mEtOffSet = (EditText) findViewById(R.id.et_offSet);
        mEtFetchSize = (EditText) findViewById(R.id.et_fetchSize);
        mBtnStartRequest = (Button) findViewById(R.id.btn_start_request);
        mTvResult = (TextView) findViewById(R.id.tv_result);
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
        String activityId = mEtActivityId.getText().toString().trim();
        String activityName = mEtActivityName.getText().toString().trim();
        String activityStatus = mEtActivityStatus.getText().toString().trim();
        String offset = mEtOffSet.getText().toString().trim();
        String fetchSize = mEtFetchSize.getText().toString().trim();

        TreeMap<String, String> treeMap = new TreeMap<>();
        if (!TextUtils.isEmpty(activityId)) {
            treeMap.put(LeUrlUtils.LE_LIVE_PARAMS_ACTIVITY_ID, activityId);
        }
        if (!TextUtils.isEmpty(activityName)) {
            treeMap.put(LeUrlUtils.LE_LIVE_PARAMS_ACTIVITY_NAME, activityName);
        }
        if (TextUtils.isEmpty(activityStatus)) {
            treeMap.put(LeUrlUtils.LE_LIVE_PARAMS_ACTIVITYSTATUS, activityStatus);
        }
        if (TextUtils.isEmpty(offset)) {
            treeMap.put(LeUrlUtils.LE_LIVE_PARAMS_OFFSET, offset);
        }
        if (TextUtils.isEmpty(fetchSize)) {
            treeMap.put(LeUrlUtils.LE_LIVE_PARAMS_FETCHSIZE, fetchSize);
        }

        requestData(LeUrlUtils.getLeLiveSearchUrl(treeMap));
    }

    @Override
    protected void addListener() {
        mBtnStartRequest.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_le_live_search_activty;
    }

//    @Override
//    protected void onSuccess(String response) {
//        ArrayList<LeLiveSearchBean> resultBean = (ArrayList<LeLiveSearchBean>) new Gson().fromJson(response, getType());
//        if (resultBean == null)
//            return;
//        parseData(resultBean);
//    }


    @Override
    protected void parseData(ArrayList<LeLiveSearchBean> data) {
        mTvResult.setText(data.toString());
    }

    @Override
    protected Type getType() {
        return new TypeToken<ArrayList<LeLiveSearchBean>>() {
        }.getType();
    }
}
