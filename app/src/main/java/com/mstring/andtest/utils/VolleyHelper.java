package com.mstring.andtest.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mstring.andtest.ApiApplication;

/**
 * Created by 李宗源 on 2016/9/28.
 */

public class VolleyHelper {
    private static RequestQueue mRequestQueue;

    public static RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getContext());
            mRequestQueue.start();
        }
        return mRequestQueue;
    }

    public static Context getContext() {
        return ApiApplication.getContext();
    }

    public static void addRequestQueue(Request<? extends Object> request) {
        getRequestQueue().add(request);
    }

    public static void stringRequestByGet(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        addRequestQueue(new StringRequest(Request.Method.GET, url, listener, errorListener));
    }
}
