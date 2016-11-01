package com.mstring.andtest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mstring.andtest.utils.TLog;

import butterknife.ButterKnife;

/**
 * Created by 李宗源 on 2016/9/28.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener<String>, Response.ErrorListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        addListener();
        initData();
    }

    protected abstract int getLayoutId();

    protected void initView() {
    }

    protected void addListener() {
    }

    protected void initData() {
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {
//        Toast.makeText(this, "网络请求失败！", Toast.LENGTH_SHORT).show();
        TLog.d("zyzd", "BaseActivity>>onErrorResponse--> " + error.toString());
        TLog.d("zyzd", "BaseActivity>>onErrorResponse--> " + error.getLocalizedMessage());
    }

    @Override
    public void onResponse(String response) {
    }
}
