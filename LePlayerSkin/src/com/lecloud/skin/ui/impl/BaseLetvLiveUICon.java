package com.lecloud.skin.ui.impl;

import android.content.Context;
import android.util.AttributeSet;

import com.lecloud.skin.ui.ILetvLiveUICon;
import com.lecloud.skin.ui.LetvLiveUIListener;
import com.lecloud.skin.ui.view.V4LargeLiveMediaController;
import com.lecloud.skin.ui.view.V4SmallLiveMediaController;

public class BaseLetvLiveUICon extends LetvUICon implements ILetvLiveUICon {

	protected LetvLiveUIListener mLetvVodUIListener;
	public BaseLetvLiveUICon(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setIsLive(true);
	}

	public BaseLetvLiveUICon(Context context, AttributeSet attrs) {
		super(context, attrs);
		setIsLive(true);
	}

	public BaseLetvLiveUICon(Context context) {
		super(context);
		setIsLive(true);
	}

	@Override
	public void setLetvLiveUIListener(LetvLiveUIListener mLetvLiveUIListener) {
		this.mLetvVodUIListener = mLetvLiveUIListener;
		if (mLetvVodUIListener != null) {
			if (mLargeMediaController != null) {
				mLargeMediaController.setLetvUIListener(mLetvVodUIListener);
			}
			if (mSmallMediaController != null) {
				mSmallMediaController.setLetvUIListener(mLetvVodUIListener);
			}
			if (mV4TopTitleView != null) {
				mV4TopTitleView.setLetvUIListener(mLetvVodUIListener);
			}
		}
		super.setLetvUIListener(mLetvLiveUIListener);
	}

    @Override
    public void setTimeShiftChange(long serverTime, long currentTime, long begin) {
        ((V4LargeLiveMediaController)mLargeMediaController).setTimeShiftChange(serverTime, currentTime, begin);
        ((V4SmallLiveMediaController)mSmallMediaController).setTimeShiftChange(serverTime, currentTime, begin);
    }

    /**
     * 隐藏除播放 缩放按钮外全部按钮
     * 
     */
	@Override
    public void showController(boolean isShow){
    	if(mLargeMediaController!= null){
    		((V4LargeLiveMediaController)mLargeMediaController).showController(isShow);
    	}
    	if(mSmallMediaController!= null){
    		((V4SmallLiveMediaController)mSmallMediaController).showController(isShow);
    	}
    }
}
