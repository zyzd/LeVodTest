package com.mstring.andtest.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseNetActivity;
import com.mstring.andtest.bean.LeImageGetBean;
import com.mstring.andtest.bean.LeResultBean;
import com.mstring.andtest.bean.LeVideoGetBean;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.TLog;
import com.mstring.andtest.utils.VolleyHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 李宗源 on 2016/9/28.
 */

public class ImageGetActivity extends BaseNetActivity<LeImageGetBean> {

    private Button mBtnStartRequest;
    private TextView mTvResult;
    private EditText mEtVideoId;
    private EditText mEtImageSize;
    private ImageView mImageView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_get;
    }

    @Override
    protected void initView() {
        mEtVideoId = (EditText) findViewById(R.id.et_video_id);
        mEtImageSize = (EditText) findViewById(R.id.et_image_size);
        mBtnStartRequest = (Button) findViewById(R.id.btn_start_request);
        mImageView = (ImageView) findViewById(R.id.imageView);
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

        if (TextUtils.isEmpty(videoId)) {
            Toast.makeText(this, "videoId不可以为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        String imageSize = mEtImageSize.getText().toString().trim();
        switch (imageSize){
            case "100_100":
            case "200_200":
            case "300_300":
            case "120_90":
            case "128_96":
            case "132_99":
            case "160_120":
            case "200_150":
            case "400_300":
            case "160_90":
            case "320_180":
            case "640_360":
            case "90_120":
            case "120_160":
            case "150_200":
            case "300_400":
                break;
            default:
                Toast.makeText(this, "尺寸格式不合法！规格有100_100、200_200、300_300、120_90、128_96、132_99" +
                        "、160_120、200_150、400_300、160_90、320_180、640_360、90_120、150_200、300_400", Toast.LENGTH_SHORT).show();
                return;
        }

        String url = "";
        try {
            url = LeUrlUtils.getLeImageGetUrl(Integer.parseInt(videoId),imageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "vidoId格式错误！", Toast.LENGTH_SHORT).show();
            return;
        }
        TLog.d("zyzd", "VideoListActivity>>url--> " + url);
        VolleyHelper.stringRequestByGet(url,this,this);
    }

    @Override
    protected void parseData(LeImageGetBean data) {
        Glide.with(this).load(data.getImg1()).into(mImageView);
        mTvResult.setText(data.toString());

    }

    @Override
    protected Type getType() {
        return new TypeToken<LeResultBean<LeImageGetBean>>(){}.getType();
    }


}
