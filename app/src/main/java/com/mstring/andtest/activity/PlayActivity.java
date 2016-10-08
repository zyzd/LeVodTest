package com.mstring.andtest.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lecloud.sdk.constant.PlayerEvent;
import com.lecloud.sdk.constant.PlayerParams;
import com.lecloud.sdk.videoview.IMediaDataVideoView;
import com.lecloud.sdk.videoview.VideoViewListener;
import com.lecloud.sdk.videoview.base.BaseMediaDataVideoView;
import com.lecloud.sdk.videoview.vod.VodVideoView;
import com.lecloud.skin.videoview.pano.vod.PanoVodVideoView;
import com.lecloud.skin.videoview.pano.vod.UIPanoVodVideoView;
import com.lecloud.skin.videoview.vod.UIVodVideoView;
import com.mstring.andtest.R;
import com.mstring.andtest.utils.VideoLayoutParams;

import java.util.LinkedHashMap;
import java.util.Map;


public class PlayActivity extends Activity {
    public final static String DATA = "data";

    private IMediaDataVideoView videoView;
    VideoViewListener mVideoViewListener = new VideoViewListener() {


        @Override
        public void onStateResult(int event, Bundle bundle) {
            handleVideoInfoEvent(event, bundle);// 处理视频信息事件
            handlePlayerEvent(event, bundle);// 处理播放器事件
            handleLiveEvent(event, bundle);// 处理直播类事件,如果是点播，则这些事件不会回调

        }

        @Override
        public String onGetVideoRateList(LinkedHashMap<String, String> map) {
            for(Map.Entry<String,String> rates:map.entrySet()){
                if(rates.getValue().equals("高清")){
                    return rates.getKey();
                }
            }
            return "";
        }
    };
    private String mPlayUrl;
    private Bundle mBundle;
    private int mPlayMode;
    private boolean mHasSkin;
    private boolean mPano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_play);
        initData();
        mHasSkin = getIntent().getBundleExtra(DATA).getBoolean("hasSkin");
        mPano = getIntent().getBundleExtra(DATA).getBoolean("pano");
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mBundle = intent.getBundleExtra(DATA);
            if (mBundle == null) {
                Toast.makeText(this, "no data", Toast.LENGTH_LONG).show();
                return;
            } else {
                mPlayUrl = mBundle.getString("path");
                mPlayMode = mBundle.getInt(PlayerParams.KEY_PLAY_MODE, -1);
            }
        }
    }

    private void initView() {
        switch (mPlayMode) {
//            case PlayerParams.VALUE_PLAYER_LIVE: {
//                videoView = mHasSkin ? (mPano ? new UIPanoLiveVideoView(this) : new UILiveVideoView(this)) : (mPano ? new PanoLiveVideoView(this) : new LiveVideoView(this));
//                break;
//            }
            case PlayerParams.VALUE_PLAYER_VOD: {
                videoView = mHasSkin ? (mPano ? new UIPanoVodVideoView(this) : new UIVodVideoView(this)) : (mPano ? new PanoVodVideoView(this) : new VodVideoView(this));
                break;
            }
//            case PlayerParams.VALUE_PLAYER_ACTION_LIVE: {
//                videoView = mHasSkin ? (mPano ? new UIPanoActionLiveVideoView(this) : new UIActionLiveVideoView(this)) : (mPano ? new PanoActionLiveVideoView(this) : new ActionLiveVideoView(this));
//                videoView.setCacheWatermark(800,200);
//                videoView.setMaxDelayTime(1000);
//                videoView.setCachePreSize(500);
//                videoView.setCacheMaxSize(10000);
//                break;
//            }
            default:
                videoView = new BaseMediaDataVideoView(this);
                break;
        }

        videoView.setVideoViewListener(mVideoViewListener);

        final RelativeLayout videoContainer = (RelativeLayout) findViewById(R.id.videoContainer);
        videoContainer.addView((View) videoView, VideoLayoutParams.computeContainerSize(this, 16, 9));

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(videoView != null){
                    videoView.resetPlayer();
                    mBundle.putString(PlayerParams.KEY_PLAY_UUID, "hxn7psp8ot");
                    mBundle.putString(PlayerParams.KEY_PLAY_VUID, "49bf3407cb");
//                    mBundle.putString(PlayerParams.KEY_PLAY_UUID, "hy07q1az4m");
//                    mBundle.putString(PlayerParams.KEY_PLAY_VUID, "03ec81c59d");
                    videoView.setDataSource(mBundle);
                }
            }
        });
        if (!TextUtils.isEmpty(mPlayUrl)) {
            videoView.setDataSource(mPlayUrl);
        } else {
            videoView.setDataSource(mBundle);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(videoView!=null){
            videoView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(videoView!=null){
            videoView.onPause();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.onDestroy();
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (videoView != null) {
            videoView.onConfigurationChanged(newConfig);
        }
    }

    /**
     * 处理播放器本身事件，具体事件可以参见IPlayer类
     */
    private void handlePlayerEvent(int state, Bundle bundle) {
        switch (state) {
            case PlayerEvent.PLAY_VIDEOSIZE_CHANGED:
                /**
                 * 获取到视频的宽高的时候，此时可以通过视频的宽高计算出比例，进而设置视频view的显示大小。
                 * 如果不按照视频的比例进行显示的话，(以surfaceView为例子)内容会填充整个surfaceView。
                 * 意味着你的surfaceView显示的内容有可能是拉伸的
                 */
                break;

            case PlayerEvent.PLAY_PREPARED:
                // 播放器准备完成，此刻调用start()就可以进行播放了
                if (videoView != null) {
                    videoView.onStart();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 处理直播类事件
     */
    private void handleLiveEvent(int state, Bundle bundle) {
    }

    /**
     * 处理视频信息类事件
     */
    private void handleVideoInfoEvent(int state, Bundle bundle) {
    }

}
