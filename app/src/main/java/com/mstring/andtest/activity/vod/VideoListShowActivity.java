package com.mstring.andtest.activity.vod;

import android.os.Bundle;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.lecloud.sdk.constant.PlayerParams;
import com.mstring.andtest.adapter.VideoListAdapter;
import com.mstring.andtest.base.BaseAdapter;
import com.mstring.andtest.base.ImitateGridViewDemoActivity;
import com.mstring.andtest.bean.LeResultBean;
import com.mstring.andtest.bean.LeVideoGetBean;
import com.mstring.andtest.utils.LeUrlUtils;
import com.mstring.andtest.utils.TLog;
import com.mstring.andtest.utils.UIHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

/**
 * Created by 李宗源 on 2016/10/9.
 */

public class VideoListShowActivity extends ImitateGridViewDemoActivity<LeVideoGetBean> {
    @Override
    protected BaseAdapter<LeVideoGetBean> getAdapter() {
        return new VideoListAdapter(this);
    }

    @Override
    public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
        super.onItemClick(familiarRecyclerView, view, position);
        UIHelper.openPlayActivity(this,getBundle(mAdapter.mDatas.get(position)));
    }

    private Bundle getBundle(LeVideoGetBean data) {
        Bundle bundle = new Bundle();
        bundle.putInt(PlayerParams.KEY_PLAY_MODE, PlayerParams.VALUE_PLAYER_VOD);

        bundle.putString(PlayerParams.KEY_PLAY_UUID, LeUrlUtils.user_unique);
        bundle.putString(PlayerParams.KEY_PLAY_VUID, data.getVideo_unique());
        bundle.putString(PlayerParams.KEY_PLAY_CHECK_CODE, "");
        bundle.putString(PlayerParams.KEY_PLAY_PAYNAME, data.getVideo_name());
        bundle.putString(PlayerParams.KEY_PLAY_USERKEY, LeUrlUtils.userid);
//			bundle.putString(PlayerParams.KEY_PLAY_BUSINESSLINE, "101");
        bundle.putString(PlayerParams.KEY_PLAY_PU, "0");

        bundle.putBoolean("pano", false);
        bundle.putBoolean("hasSkin", true);
        return bundle;
    }

    @Override
    protected Type getType() {
        return  new TypeToken<LeResultBean<ArrayList<LeVideoGetBean>>>() {
        }.getType();
    }

    @Override
    protected String getUrl(boolean isRefresh, int index, int size) {
        String url = LeUrlUtils.getVideoListUrl(null,10, index, size);
        TLog.d("zyzd", "VideoListShowActivity>>getUrl--> " + url);
        return url;
    }

    @Override
    protected int getPageSize() {
        return 10;
    }
}
