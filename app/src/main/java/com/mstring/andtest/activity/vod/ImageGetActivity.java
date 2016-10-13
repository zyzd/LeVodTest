package com.mstring.andtest.activity.vod;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseNetActivity;
import com.mstring.andtest.bean.LeImageGetBean;
import com.mstring.andtest.bean.LeResultBean;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.TLog;
import com.mstring.andtest.utils.VolleyHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by 李宗源 on 2016/9/28.
 */

public class ImageGetActivity extends BaseNetActivity<LeImageGetBean> {

    private Button mBtnStartRequest;
//    private TextView mTvResult;
    private EditText mEtVideoId;
    private EditText mEtImageSize;
    public GridView mGridView;
    //    private ImageView mImageView;
    private ArrayList<String> mDatas = new ArrayList<>();
    public ImageAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_get;
    }

    @Override
    protected void initView() {
        mEtVideoId = (EditText) findViewById(R.id.et_video_id);
        mEtImageSize = (EditText) findViewById(R.id.et_image_size);
        mBtnStartRequest = (Button) findViewById(R.id.btn_start_request);
//        mImageView = (ImageView) findViewById(R.id.imageView);
        mGridView = (GridView) findViewById(R.id.gridview);
//        mTvResult = (TextView) findViewById(R.id.tv_result);

        mAdapter = new ImageAdapter();
        mGridView.setAdapter(mAdapter);
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

        String imageSize = mEtImageSize.getText().toString().trim();
        switch (imageSize) {
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
            url = LeUrlUtils.getLeImageGetUrl(Integer.parseInt(videoId), imageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "vidoId格式错误！", Toast.LENGTH_SHORT).show();
            return;
        }
        TLog.d("zyzd", "VideoListActivity>>url--> " + url);
        VolleyHelper.stringRequestByGet(url, this, this);
    }

    @Override
    protected void parseData(LeImageGetBean data) {

        if (data == null) {
            return;
        }

        mDatas.clear();
        mDatas.add(data.getImg1());
        mDatas.add(data.getImg2());
        mDatas.add(data.getImg3());
        mDatas.add(data.getImg4());
        mDatas.add(data.getImg5());
        mDatas.add(data.getImg6());
        mDatas.add(data.getImg7());
        mDatas.add(data.getImg8());

        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected Type getType() {
        return new TypeToken<LeResultBean<LeImageGetBean>>() {
        }.getType();
    }

    public class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public String getItem(int position) {
            return getItem(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(ImageGetActivity.this).inflate(R.layout.item_image_get, parent, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            Glide.with(ImageGetActivity.this).load(mDatas.get(position)).into(imageView);
            return view;
        }
    }


}
