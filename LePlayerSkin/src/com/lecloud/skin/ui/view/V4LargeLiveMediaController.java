package com.lecloud.skin.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;

import com.lecloud.skin.R;
import com.lecloud.skin.ui.LetvUIListener;
import com.lecloud.skin.ui.base.BaseChangeModeBtn;
import com.lecloud.skin.ui.base.BaseChgScreenBtn;
import com.lecloud.skin.ui.base.BaseLiveSeekBar;
import com.lecloud.skin.ui.base.BaseLiveSeekBar.OnSeekChangeListener;
import com.lecloud.skin.ui.base.BaseMediaController;
import com.lecloud.skin.ui.base.BasePlayBtn;
import com.lecloud.skin.ui.base.BaseRateTypeBtn;

import java.util.List;

public class V4LargeLiveMediaController extends BaseMediaController {
	protected BaseLiveSeekBar mBaseLiveSeekBar;
	protected View mBackToLive;
			
	/**上次是否显示了seekbar，用于隐藏后再次显示seekbar*/
	private boolean isLastShowSeekBar = false;
	/**上次是否显示了rateTypeBtn，用于隐藏后再次显示rateTypeBtn*/
	private boolean isLastShowRateTypeBtn = false;
	/**上次是否显示了ChangeModeBtn，用于隐藏后再次显示ChangeModeBtn*/
	private boolean isLastShowChangeModeBtn = false;
	
    public V4LargeLiveMediaController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public V4LargeLiveMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public V4LargeLiveMediaController(Context context) {
        super(context);
    }

	@Override
	protected void onInitView() {
		mBasePlayBtn = (BasePlayBtn) findViewById(R.id.vnew_play_btn);
		mBaseChgScreenBtn = (BaseChgScreenBtn) findViewById(R.id.vnew_chg_btn);
		mBaseRateTypeBtn = (BaseRateTypeBtn) findViewById(R.id.vnew_rate_btn);
		mBaseLiveSeekBar = (BaseLiveSeekBar) findViewById(R.id.vnew_seekbar);
		mBaseChangeModeBtn = (BaseChangeModeBtn) findViewById(R.id.vnew_change_mode);
		mBaseChangeVRModeBtn = (BaseChangeModeBtn) findViewById(R.id.vnew_change_vr_mode);
		mBackToLive = findViewById(R.id.vnew_back_to_live);
		mBackToLive.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                mLetvUIListener.resetPlay();
            }
        });
		mBaseLiveSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                
            }

            @Override
            public void onShowBackToLive(boolean show) {
                mBackToLive.setVisibility(show?VISIBLE:INVISIBLE);
            }
        });
		mBaseChgScreenBtn.showZoomOutState();
	}
	
	@Override
	public void setLetvUIListener(LetvUIListener mLetvUIListener) {
		this.mLetvUIListener = mLetvUIListener;
		if (mLetvUIListener != null) {
			if (mBasePlayBtn != null) {
				mBasePlayBtn.setLetvUIListener(mLetvUIListener);
			}
			if (mBaseChgScreenBtn != null) {
				mBaseChgScreenBtn.setLetvUIListener(mLetvUIListener);
			}
			if (mBaseRateTypeBtn != null) {
				mBaseRateTypeBtn.setLetvUIListener(mLetvUIListener);
			}
			if (mBaseLiveSeekBar != null) {
			    mBaseLiveSeekBar.setLetvUIListener(mLetvUIListener);
			}
			if (mBaseChangeModeBtn != null) {
				mBaseChangeModeBtn.setLetvUIListener(mLetvUIListener);
			}
			if (mBaseChangeVRModeBtn != null) {
				mBaseChangeVRModeBtn.setLetvUIListener(mLetvUIListener);
			}
		}
	}
	
	@Override
	public void setRateTypeItems(List<String> ratetypes,String definition) {
		if (mBaseRateTypeBtn != null) {
			mBaseRateTypeBtn.setRateTypeItems(ratetypes, definition);
		}
	}

	@Override
	public void setPlayState(boolean isPlayState) {
		if (mBasePlayBtn != null) {
			mBasePlayBtn.setPlayState(isPlayState);
		}
	}

	@Override
	public void setCurrentPosition(long position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDuration(long duration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBufferPercentage(long bufferPercentage) {
		
		
	}
	
    public void setTimeShiftChange(long serverTime, long currentTime, long begin) {
        if (mBaseLiveSeekBar != null) {
        	if(!isLastShowSeekBar){
        		mBaseLiveSeekBar.setVisibility(VISIBLE);
        	}
            mBaseLiveSeekBar.setTimeShiftChange(serverTime, currentTime, begin);
        }
    }
    
    public BaseLiveSeekBar getSeekbar() {
        return mBaseLiveSeekBar;
    }

    
    public void showController(boolean isShow){
    	if(isShow){
            if (mBaseLiveSeekBar != null && isLastShowSeekBar) {
                mBaseLiveSeekBar.setVisibility(VISIBLE);
                mBaseLiveSeekBar.reset();
            }
            if(mBaseRateTypeBtn != null && isLastShowRateTypeBtn){
                mBaseRateTypeBtn.setVisibility(VISIBLE);
            }
            if(mBaseChangeModeBtn != null && isLastShowChangeModeBtn){
            	mBaseChangeModeBtn.setVisibility(VISIBLE);
            }
			if(mBaseChangeVRModeBtn != null && isLastShowChangeModeBtn){
				mBaseChangeVRModeBtn.setVisibility(VISIBLE);
			}
    	}else{
    		if (mBaseLiveSeekBar != null) {
    			if(mBaseLiveSeekBar.getVisibility() == View.VISIBLE){
    				isLastShowSeekBar = true;
    			}else{
    				isLastShowSeekBar = false;
    			}
                mBaseLiveSeekBar.setVisibility(GONE);
            }
            if(mBaseRateTypeBtn != null){
            	if(mBaseRateTypeBtn.getVisibility() == View.VISIBLE){
            		isLastShowRateTypeBtn = true;
    			}else{
    				isLastShowRateTypeBtn = false;
    			}
                mBaseRateTypeBtn.setVisibility(INVISIBLE);
            }
            if(mBaseChangeModeBtn != null){
            	if(mBaseChangeModeBtn.getVisibility() == View.VISIBLE){
            		isLastShowChangeModeBtn = true;
    			}else{
    				isLastShowChangeModeBtn = false;
    			}
            	mBaseChangeModeBtn.setVisibility(GONE);
            }
			if(mBaseChangeVRModeBtn != null){
				if(mBaseChangeVRModeBtn.getVisibility() == View.VISIBLE){
					isLastShowChangeModeBtn = true;
				}else{
					isLastShowChangeModeBtn = false;
				}
				mBaseChangeVRModeBtn.setVisibility(GONE);
			}
            if(mBackToLive != null){
            	mBackToLive.setVisibility(INVISIBLE);
            }
    	}
    }
    
}
