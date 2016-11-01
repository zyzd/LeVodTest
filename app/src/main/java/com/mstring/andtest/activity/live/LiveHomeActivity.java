package com.mstring.andtest.activity.live;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mstring.andtest.R;
import com.mstring.andtest.utils.UIHelper;

public class LiveHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_home);
    }

    public void create(View view) {
        UIHelper.openLeLiveCreateActivty(this);
    }

    public void search(View view) {
        UIHelper.openLeLiveSearchActivty(this);
    }

    public void modify(View view) {
        UIHelper.openLeLiveModifyActiMvty(this);
    }

    public void modifyCoverImg(View view) {
        UIHelper.openLeLiveModifyCoverImgActivty(this);
    }

    public void stop(View view) {
        UIHelper.openLeLiveStopActivity(this);
    }

    public void getUrl(View view) {
        UIHelper.openLeLiveGetUrlActivity(this);
    }

    public void getPushToken(View view) {
        UIHelper.openLeLiveGetPushTokenActivity(this);
    }

    public void getPushUrl(View view) {
        UIHelper.openLeLiveGetPushUrlActivity(this);
    }

    public void getActivityMachineState(View view) {
        UIHelper.openLeLiveGetActivityMachineStateActivity(this);
    }

    public void streaminfoSearch(View view) {
        UIHelper.openLeLiveStreaminfoSearchActivity(this);
    }

    public void reateRecTaskMap(View view) {
        UIHelper.openLeLiveCreateRecTaskActivity(this);
    }

    public void recSearchResult(View view) {
        UIHelper.openLeLiveRecSearchResultActivity(this);
    }
    public void getPlayInfo(View view) {
        UIHelper.openLeLiveGetPlayInfoActivity(this);
    }

}
