package com.lecloud.skin.videoview.vod;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lecloud.sdk.api.md.entity.vod.VideoHolder;
import com.lecloud.sdk.constant.PlayerEvent;
import com.lecloud.sdk.constant.PlayerParams;
import com.lecloud.sdk.constant.StatusCode;
import com.lecloud.sdk.player.IAdPlayer;
import com.lecloud.sdk.player.IMediaDataPlayer;
import com.lecloud.sdk.player.IPlayer;
import com.lecloud.sdk.utils.NetworkUtils;
import com.lecloud.sdk.videoview.vod.VodVideoView;
import com.lecloud.skin.R;
import com.lecloud.skin.ui.ILetvVodUICon;
import com.lecloud.skin.ui.LetvVodUIListener;
import com.lecloud.skin.ui.impl.LetvVodUICon;
import com.lecloud.skin.ui.utils.ScreenUtils;
import com.lecloud.skin.ui.utils.timer.IChange;
import com.lecloud.skin.ui.utils.timer.LeTimerManager;
import com.lecloud.skin.ui.view.VideoNoticeView.IReplayListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class UIVodVideoView extends VodVideoView {
    public static final String TAG = "UIVodVideoView";

    private static final String PREF_SEEK_INFO = "seek_info";

    protected ILetvVodUICon letvVodUICon;
    //    protected int width = -1;
//    protected int height = -1;
    protected LeTimerManager leTimerManager;
    TextView timeTextView;
    private long lastPosition;
    private LinkedHashMap<String, String> vtypeList;
    //是否正在seeking
    private boolean isSeeking = false;

    public UIVodVideoView(Context context) {
        super(context);
        initUICon(context);
        getLeTimerManager(500);

    }

    public LeTimerManager getLeTimerManager(long delaymillts) {
        if (leTimerManager == null) {
            leTimerManager = new LeTimerManager(new IChange() {

                @Override
                public void onChange() {
                    if (letvVodUICon != null && player != null) {
                        post(new Runnable() {

                            @Override
                            public void run() {
                                letvVodUICon.setDuration(player.getDuration());
                                Log.d("meng", "isSeeking" + isSeeking);
                                if (!isSeeking && player.getCurrentPosition() <= player.getDuration()) {
                                    letvVodUICon.setCurrentPosition(player.getCurrentPosition());
                                }
                                letvVodUICon.setPlayState(player.isPlaying());
                            }
                        });
                    }
                }
            }, delaymillts);
        }
        return leTimerManager;
    }

    private void stopTimer() {
        if (leTimerManager != null) {
            leTimerManager.stop();
            leTimerManager = null;
        }
    }

    private void startTimer() {
        if (leTimerManager == null) {
            getLeTimerManager(500);
        }
        if (leTimerManager != null) {
            leTimerManager.start();
        }
    }

    private void initUICon(final Context context) {
        letvVodUICon = new LetvVodUICon(context);
//        letvVodUICon.setGravitySensor(false);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(letvVodUICon.getView(), params);
        letvVodUICon.setRePlayListener(new IReplayListener() {

            @Override
            public Bundle getReportParams() {
                return ((IMediaDataPlayer) player).getReportParams();
            }

            @Override
            public void onRePlay() {
                setLastPostion();
                player.retry();
            }
        });
        letvVodUICon.setLetvVodUIListener(new LetvVodUIListener() {

            @Override
            public void setRequestedOrientation(int requestedOrientation) {
                if (context instanceof Activity) {
                    ((Activity) context).setRequestedOrientation(requestedOrientation);
                }
            }

            @Override
            public void resetPlay() {
                // LeLog.dPrint(TAG, "--------resetPlay");
            }

            @Override
            public void onSetDefination(int positon) {
//                UIVodVideoView.super.onInterceptMediaDataSuccess(event, bundle);
                if (vtypeList != null && vtypeList.size() > 0) {
                    setLastPostion();
                    ((IMediaDataPlayer) player).setDataSourceByRate(new ArrayList<String>(vtypeList.keySet()).get(positon));
                }
            }

            @Override
            public void onSeekTo(float sec) {
                Log.d("meng", "onSeekTo" + isSeeking);
                long msec = (long) Math.floor((sec * player.getDuration()));
                if (player != null) {
                    player.seekTo(msec);
                    if (isComplete()) {
                        player.retry();
                    } else if (!player.isPlaying()) {
                        player.start();
                    }
                    ((LetvVodUICon) letvVodUICon).syncSeekProgress((int) msec);
                }
            }

            @Override
            public void onClickPlay() {
                if (player.isPlaying()) {
                    player.pause();
                } else if (isComplete()) {
                    player.seekTo(0);
                    player.retry();
                } else {
                    player.start();
                }
            }

            @Override
            public void onUIEvent(int event, Bundle bundle) {
                // TODO Auto-generated method stub

            }

            @Override
            public int onSwitchPanoControllMode(int controllMode) {
                return switchControllMode(controllMode);
            }

            @Override
            public int onSwitchPanoDisplayMode(int displayMode) {
                return switchDisplayMode(displayMode);
            }

            @Override
            public void onProgressChanged(int progress) {
                // TODO Auto-generated method stub
//				((LetvVodUICon) letvVodUICon).syncSeekProgress(progress);
            }

            @Override
            public void onStartSeek() {
                // TODO Auto-generated method stub
                isSeeking = true;
                Log.d("meng", "startSeek" + isSeeking);
            }

        });
    }

    protected int switchControllMode(int interactiveMode) {
        return -1;
    }

    protected int switchDisplayMode(int displayMode) {
        return -1;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (ScreenUtils.getOrientation(getContext()) == Configuration.ORIENTATION_PORTRAIT) {
            letvVodUICon.setRequestedOrientation(ILetvVodUICon.SCREEN_PORTRAIT, UIVodVideoView.this);
        } else {
            letvVodUICon.setRequestedOrientation(ILetvVodUICon.SCREEN_LANDSCAPE, UIVodVideoView.this);
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onInterceptVodMediaDataSuccess(int event, Bundle bundle) {
        super.onInterceptVodMediaDataSuccess(event, bundle);
        VideoHolder videoHolder = bundle.getParcelable(PlayerParams.KEY_RESULT_DATA);
        vtypeList = videoHolder.getVtypes();
        String title = videoHolder.getTitle();
        if (!TextUtils.isEmpty(title)) {
            letvVodUICon.setTitle(title);
        }
        String currentDefiniton = vtypeList.get(onInterceptSelectDefiniton(vtypeList, videoHolder.getDefaultVtype()));
        List<String> ratetypes = new ArrayList<String>(videoHolder.getVtypes().values());
        letvVodUICon.setRateTypeItems(ratetypes, currentDefiniton);
        letvVodUICon.showWaterMark(((VideoHolder) bundle.getParcelable(PlayerParams.KEY_RESULT_DATA)).getCoverConfig());
    }

    @Override
    protected void onInterceptMediaDataError(int event, Bundle bundle) {
        super.onInterceptMediaDataError(event, bundle);
        letvVodUICon.hideLoading();
        letvVodUICon.hideWaterMark();
        letvVodUICon.processMediaState(event, bundle);
    }


    @Override
    protected void notifyPlayerEvent(int event, Bundle bundle) {
        super.notifyPlayerEvent(event, bundle);
        letvVodUICon.processPlayerState(event, bundle);
        switch (event) {
            case PlayerEvent.PLAY_COMPLETION://202
                //btn pause
                letvVodUICon.setPlayState(false);
                //update progress
                if (letvVodUICon != null && player != null) {
                    letvVodUICon.setDuration(player.getDuration());
                    letvVodUICon.setCurrentPosition(player.getCurrentPosition());
                }
                lastPosition = 0;

                stopTimer();
                break;
            case PlayerEvent.PLAY_INFO:
                int code = bundle.getInt(PlayerParams.KEY_RESULT_STATUS_CODE);
                if (code == StatusCode.PLAY_INFO_VIDEO_RENDERING_START) {
                    startTimer();
                }

                switch (code) {
                    case StatusCode.PLAY_INFO_BUFFERING_START://500004
                        if (NetworkUtils.hasConnect(context) && !letvVodUICon.isShowLoading()) {
                            letvVodUICon.showLoadingProgress();
                        }
                        break;
                    case StatusCode.PLAY_INFO_BUFFERING_END://500005
                        letvVodUICon.hideLoading();
                        break;
                    case StatusCode.PLAY_INFO_VIDEO_RENDERING_START://500006
                        letvVodUICon.showWaterMark();
                        letvVodUICon.hideLoading();
                        break;
                    default:
                        break;
                }
                break;
            case PlayerEvent.PLAY_PREPARED: {
                if (NetworkUtils.hasConnect(context) && !letvVodUICon.isShowLoading()) {
                    letvVodUICon.showLoadingProgress();
                }

                if (lastPosition > 0) {
                    player.seekToLastPostion(lastPosition);
                    letvVodUICon.setDuration(player.getDuration());
                    letvVodUICon.setCurrentPosition(lastPosition);
                }
                break;
            }
            case PlayerEvent.PLAY_SEEK_COMPLETE: {//209
                setLastPostion();
                isSeeking = false;
//                Log.e("meng", "PlayerEvent.PLAY_SEEK_COMPLETE: " + lastPosition);
                break;
            }
            case PlayerEvent.PLAY_ERROR://205
                removeView(timeTextView);
                letvVodUICon.getView().setVisibility(VISIBLE);
                letvVodUICon.hideLoading();
                letvVodUICon.hideWaterMark();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onInterceptAdEvent(int code, Bundle bundle) {

        switch (code) {
            case PlayerEvent.AD_START:
                letvVodUICon.getView().setVisibility(GONE);
                letvVodUICon.hideLoading();
                LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                if (timeTextView == null) {
                    timeTextView = new TextView(context);
                    timeTextView.setBackgroundColor(Color.BLACK);
                    timeTextView.setAlpha(0.7f);
                    timeTextView.setTextColor(Color.WHITE);
                    timeTextView.setPadding(20, 20, 20, 20);
                }
                if (!timeTextView.isShown()) {
                    addView(timeTextView, lp);
                    bringChildToFront(timeTextView);
                }
                break;
            case PlayerEvent.AD_ERROR:
                if (!NetworkUtils.hasConnect(context)){
                    letvVodUICon.processPlayerState(code, bundle);
                }
            case IAdPlayer.AD_PLAY_ERROR:
            case PlayerEvent.AD_COMPLETE:
                removeView(timeTextView);
                letvVodUICon.getView().setVisibility(VISIBLE);
                letvVodUICon.hideLoading();
                letvVodUICon.hideWaterMark();
                break;
            case PlayerEvent.AD_PROGRESS:
                timeTextView.setText(getContext().getResources().getString(R.string.ad) + bundle.getInt(IAdPlayer.AD_TIME) + "s");
                break;

            default:
                break;
        }
        super.onInterceptAdEvent(code, bundle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        setLastPostion();
    }


    @Override
    public void onResume() {
        //横屏设置ILetvVodUICon.SCREEN_LANDSCAPE,竖屏设置ILetvVodUICon.SCREEN_PORTRAIT
        if(isFirstPlay){
            letvVodUICon.setRequestedOrientation(ILetvVodUICon.SCREEN_PORTRAIT, this);
            isFirstPlay = false;
        }
        super.onResume();
    }

    public boolean isComplete() {
        //TODO
        return player != null && player.getStatus() == IPlayer.PLAYER_STATUS_EOS;
    }

    private void setLastPostion() {
        if(player == null || player.getCurrentPosition()==0){
            return;
        }
        lastPosition = player.getCurrentPosition();
    }

    @Override
    public void resetPlayer() {
        super.resetPlayer();
        stopTimer();
        if (timeTextView != null) {
            timeTextView.setText("");
            removeView(timeTextView);
        }
        lastPosition = 0;
        vtypeList = null;
        isSeeking = false;
    }
}
