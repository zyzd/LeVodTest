package com.mstring.andtest.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lecloud.sdk.constant.PlayerParams;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseNetActivity;
import com.mstring.andtest.bean.LeResultBean;
import com.mstring.andtest.bean.LeVideoGetBean;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.TLog;
import com.mstring.andtest.utils.UIHelper;

import java.lang.reflect.Type;

/**
 * Created by 李宗源 on 2016/9/28.
 */

public class VideoGetActivity extends BaseNetActivity<LeVideoGetBean> {

    private Button mBtnStartRequest;
    private Button mBtnRequestAndShow;
    private EditText mEtVideoId;
    private TextView mTvResult;
    private boolean flag;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_get;
    }

    @Override
    public void initView() {
        mBtnStartRequest = (Button) findViewById(R.id.btn_start_request);
        mBtnRequestAndShow = (Button) findViewById(R.id.btn_request_and_show);
        mEtVideoId = (EditText) findViewById(R.id.et_video_id);
        mTvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    protected void addListener() {
        mBtnStartRequest.setOnClickListener(this);
        mBtnRequestAndShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_request:
                flag = true;
                startRequest();
                break;
            case R.id.btn_request_and_show:
                flag = false;
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
        if(flag){
            mTvResult.setText(data.toString());
        }else{
            UIHelper.openPlayActivity(this,getBundle(data));
        }

    }

    private Bundle getBundle(LeVideoGetBean data) {
        Bundle bundle = new Bundle();
        bundle.putInt(PlayerParams.KEY_PLAY_MODE, PlayerParams.VALUE_PLAYER_VOD);

        bundle.putString(PlayerParams.KEY_PLAY_UUID, LeUrlUtils.user_unique);
        bundle.putString(PlayerParams.KEY_PLAY_VUID, data.getVideo_unique());
        bundle.putString(PlayerParams.KEY_PLAY_CHECK_CODE, "");
        bundle.putString(PlayerParams.KEY_PLAY_PAYNAME, data.getVideo_name());
        bundle.putString(PlayerParams.KEY_PLAY_USERKEY, LeUrlUtils.userId);
//			bundle.putString(PlayerParams.KEY_PLAY_BUSINESSLINE, "101");
        bundle.putString(PlayerParams.KEY_PLAY_PU, "0");

        bundle.putBoolean("pano", false);
        bundle.putBoolean("hasSkin", true);
        return bundle;
    }

}
