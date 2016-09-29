package com.mstring.andtest.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseNetActivity;
import com.mstring.andtest.bean.LeResultBean;
import com.mstring.andtest.bean.LeVideoGetBean;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.TLog;

import java.lang.reflect.Type;

/**
 * Created by 李宗源 on 2016/9/28.
 */

public class VideoGetActivity extends BaseNetActivity<LeVideoGetBean> {

    private Button mBtnStartRequest;
    private EditText mEtVideoId;
    private TextView mTvResult;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_get;
    }

    @Override
    public void initView() {
        mBtnStartRequest = (Button) findViewById(R.id.btn_start_request);
        mEtVideoId = (EditText) findViewById(R.id.et_video_id);
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
        String videoId = mEtVideoId.getText().toString().trim();

        if (TextUtils.isEmpty(videoId)) {
            Toast.makeText(this, "videoId不可以为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = "";
        try {
            url = LeUrlUtils.getVideoGetUrl(Integer.parseInt(videoId));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "vidoId格式错误！", Toast.LENGTH_SHORT).show();
            return;
        }
        TLog.d("zyzd", "VideoGetActivity>>url--> " + url);
        requestData(url);
    }

    @Override
    public Type getType() {
        return new TypeToken<LeResultBean<LeVideoGetBean>>() {
        }.getType();
    }

    @Override
    protected void parseData(LeVideoGetBean data) {
        mTvResult.setText(data.toString());
    }

}
