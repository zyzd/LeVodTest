package com.mstring.andtest.base;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mstring.andtest.utils.TLog;
import com.mstring.andtest.utils.VolleyHelper;

import java.lang.reflect.Type;


/**
 * Created by 李宗源 on 2016/9/28.
 */

public abstract class BaseLeLiveNetActivity<T> extends BaseActivity {

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

    protected void onSuccess(String response) {
        if(TextUtils.isEmpty(response)){
            TLog.d("zyzd", "BaseLeLiveNetActivity>>onSuccess--> " + "response is empty!");
            return;
        }
        TLog.d("zyzd", "BaseLeLiveNetActivity>>onSuccess--> " + response);
        T resultBean = null;
        try {
            resultBean = (T) new Gson().fromJson(response, getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            TLog.d("zyzd", "BaseLeLiveNetActivity>>onSuccess--> 数据解析异常！" );
        }

        if (resultBean == null)
            return;

        parseData(resultBean);
    }

    protected abstract void parseData(T data);

    protected abstract Type getType();

}
