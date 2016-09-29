package com.mstring.andtest.base;

import android.net.Uri;

import com.google.gson.Gson;
import com.mstring.andtest.bean.LeResultBean;
import com.mstring.andtest.utils.TLog;
import com.mstring.andtest.utils.VolleyHelper;

import java.lang.reflect.Type;


/**
 * Created by 李宗源 on 2016/9/28.
 */

public abstract class BaseNetActivity<T> extends BaseActivity {

    /**
     * 请求数据
     *
     * @param url
     */
    protected void requestData(String url) {
        VolleyHelper.stringRequestByGet(url, this, this);
    }

    @Override
    public void onResponse(String response) {
        onSuccess(response);
    }

    private void onSuccess(String response) {
        LeResultBean<T> resultBean = (LeResultBean<T>) new Gson().fromJson(response, getType());
        if (resultBean == null || resultBean.getCode() != 0)
            return;
        if (resultBean.getData() == null)
            return;
        parseData(resultBean.getData());
    }

    protected abstract void parseData(T data);

    protected abstract Type getType();

}
