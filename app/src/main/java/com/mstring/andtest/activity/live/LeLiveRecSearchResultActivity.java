package com.mstring.andtest.activity.live;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseLeLiveNetActivity;
import com.mstring.andtest.bean.LeLiveRecSearchResultBean;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.VolleyHelper;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.TreeMap;

import static com.mstring.andtest.utils.DateUtil.transformDate;
import static com.mstring.andtest.utils.LeUrlUtils.LE_LIVE_PARAMS_END_TIME;
import static com.mstring.andtest.utils.LeUrlUtils.LE_LIVE_PARAMS_LIVEID;
import static com.mstring.andtest.utils.LeUrlUtils.LE_LIVE_PARAMS_OFFSET_MIN;
import static com.mstring.andtest.utils.LeUrlUtils.LE_LIVE_PARAMS_SIZE;
import static com.mstring.andtest.utils.LeUrlUtils.LE_LIVE_PARAMS_START_TIME;
import static com.mstring.andtest.utils.LeUrlUtils.LE_LIVE_PARAMS_TASKID;

/**
 * Created by 李宗源 on 2016/11/1.
 */

public class LeLiveRecSearchResultActivity extends BaseLeLiveNetActivity<LeLiveRecSearchResultBean> {

    public EditText mEtLiveId;
    public EditText mEtTaskId;
    public EditText mEtOffset;
    public EditText mEtSize;
    public EditText mEtStartTime;
    public EditText mEtEndTime;
    public Button mBtnStartRequest;
    public TextView mTvResult;

    @Override
    protected void initView() {
        super.initView();
        mEtLiveId = (EditText) findViewById(R.id.et_liveId);
        mEtTaskId = (EditText) findViewById(R.id.et_taskId);
        mEtOffset = (EditText) findViewById(R.id.et_offset);
        mEtSize = (EditText) findViewById(R.id.et_size);
        mEtStartTime = (EditText) findViewById(R.id.et_startTime);
        mEtEndTime = (EditText) findViewById(R.id.et_endTime);
        mBtnStartRequest = (Button) findViewById(R.id.btn_start_request);
        mTvResult = (TextView) findViewById(R.id.tv_result);

        mEtStartTime.setText("");
        mEtEndTime.setText("");
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

        mTvResult.setText("");

        TreeMap<String, String> params = new TreeMap<>();
        //直播ID
        String liveId = mEtLiveId.getText().toString().trim();
        if (!TextUtils.isEmpty(liveId)) {
            params.put(LE_LIVE_PARAMS_LIVEID, liveId);
        }
        //任务ID
        String taskId = mEtTaskId.getText().toString().trim();
        if (!TextUtils.isEmpty(taskId)) {
            params.put(LE_LIVE_PARAMS_TASKID, taskId);
        }
        //开始行数
        String offset = mEtOffset.getText().toString().trim();
        if (!TextUtils.isEmpty(offset)) {
            params.put(LE_LIVE_PARAMS_OFFSET_MIN, offset);
        }
        //每页记录数
        String size = mEtSize.getText().toString().trim();
        if (!TextUtils.isEmpty(size)) {
            params.put(LE_LIVE_PARAMS_SIZE, size);
        }
        //开始时间，从1970开始的毫秒数
        String startTime = mEtStartTime.getText().toString().trim();
        if (!TextUtils.isEmpty(startTime)) {
            Date startDate = transformDate(startTime);
            if (startDate != null) {
                params.put(LE_LIVE_PARAMS_START_TIME, startDate.getTime() + "");
            }
        }
        //结束时间，从1970开始的毫秒数
        String endTime = mEtEndTime.getText().toString().trim();
        if (!TextUtils.isEmpty(endTime)) {
            Date endDate = transformDate(endTime);
            if (endDate != null) {
                params.put(LE_LIVE_PARAMS_END_TIME, endDate.getTime() + "");
            }
        }

        String recSearchResultUrl = LeUrlUtils.getLiveRecSearchResultUrl(params);

        VolleyHelper.stringRequestByGet(recSearchResultUrl, this, this);

    }

    @Override
    protected void parseData(LeLiveRecSearchResultBean data) {
        mTvResult.setText(data.toString());
    }

    @Override
    protected Type getType() {
        return new TypeToken<LeLiveRecSearchResultBean>() {
        }.getType();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_le_live_rec_search_result;
    }
}
