package com.mstring.andtest.activity.live;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mstring.andtest.R;
import com.mstring.andtest.utils.TLog;
import com.mstring.andtest.utils.UIHelper;
import com.mstring.andtest.utils.VolleyHelper;

import java.util.Map;
import java.util.TreeMap;

import static android.os.Build.VERSION_CODES.M;

public class LiveHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_home);
    }

    public void create(View view){
        UIHelper.openLeLiveCreateActivty(this);
    }
    public void search(View view){
        UIHelper.openLeLiveSearchActivty(this);
    }
    public void modify(View view){
        UIHelper.openLeLiveModifyActiMvty(this);
    }
    public void modifyCoverImg(View view){
        UIHelper.openLeLiveModifyCoverImgActivty(this);
    }

}
