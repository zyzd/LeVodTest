package com.mstring.andtest.base;

import com.google.gson.Gson;
import com.mstring.andtest.bean.LeResultBean;
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

        T resultBean = (T) new Gson().fromJson(response, getType());

        if (resultBean == null)
            return;

        parseData(resultBean);
    }

    protected abstract void parseData(T data);

    protected abstract Type getType();

}
