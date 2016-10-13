package com.mstring.andtest.activity.vod;

import com.google.gson.reflect.TypeToken;
import com.mstring.andtest.adapter.DataTotalAdapter;
import com.mstring.andtest.base.BaseAdapter;
import com.mstring.andtest.base.ImitateGridViewDemoActivity;
import com.mstring.andtest.bean.LeDataTotalDateBean;
import com.mstring.andtest.bean.LeResultBean;
import com.mstring.andtest.utils.LeUrlUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 获取以天为单位的所有数据
 * Created by 李宗源 on 2016/9/29.
 */

//public class DataTotalDateActivity extends BaseNetActivity<ArrayList<LeDataTotalDateBean>> {
public class DataTotalDateActivity extends ImitateGridViewDemoActivity<LeDataTotalDateBean> {
    @Override
    protected BaseAdapter getAdapter() {
        return new DataTotalAdapter(this);
    }

    @Override
    protected Type getType() {
        return new TypeToken<LeResultBean<ArrayList<LeDataTotalDateBean>>>() {
        }.getType();
    }

    @Override
    protected String getUrl(boolean isRefresh, int index, int size) {
        String startDate = "2016-09-20";
        String endDate = "2016-09-27";
        return LeUrlUtils.getLeDataTotalDateUrl(startDate, endDate, index, size);

    }

    @Override
    protected int getPageSize() {
        return 10;
    }

//    private EditText mEtStartDate;
//    private EditText mEtEndDate;
//    private Button mBtnStartRequest;
//    private TextView mTvResult;
//
//    @Override
//    protected void initView() {
//        View view = findViewById(R.id.til_video_id);
//        view.setVisibility(View.GONE);
//        mEtStartDate = (EditText) findViewById(R.id.et_start_date);
//        mEtEndDate = (EditText) findViewById(R.id.et_end_date);
//        mBtnStartRequest = (Button) findViewById(R.id.btn_start_request);
//        mTvResult = (TextView) findViewById(R.id.tv_result);
//    }
//
//    @Override
//    protected void addListener() {
//        mBtnStartRequest.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_start_request:
//                startRequest();
//                break;
//        }
//    }
//
//    private void startRequest() {
//        String startDate = mEtStartDate.getText().toString().trim();
//        String endDate = mEtEndDate.getText().toString().trim();
//
//        String url = LeUrlUtils.getLeDataTotalDateUrl(startDate, endDate, 1, 10);
//        TLog.d("zyzd", "DataTotalDateActivity>>startRequest--> url:" + url);
//        requestData(url);
//    }
//
//
//    @Override
//    protected void parseData(ArrayList<LeDataTotalDateBean> data) {
//        TLog.d("zyzd", "DataTotalDateActivity>>parseData--> " + data);
//        mTvResult.setText(data.toString());
//    }
//
//
//    @Override
//    protected Type getType() {
//        return new TypeToken<LeResultBean<ArrayList<LeDataTotalDateBean>>>() {
//        }.getType();
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_data_video_date;
//    }
}
