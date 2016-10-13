package com.mstring.andtest.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mstring.andtest.ApiApplication;

import java.util.Map;
import java.util.TreeMap;

import static android.os.Build.VERSION_CODES.M;

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

    public static void stringRequestByPost(String url, final Map<String,String> params, Response.Listener<String> listener, Response.ErrorListener errorListener){
        addRequestQueue(new StringRequest(Request.Method.POST, url, listener, errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                TreeMap<String, String> headerMap = new TreeMap<>();
                headerMap.put("Content-Type","application/x-www-form-urlencoded");
                headerMap.put("charset","utf-8");
                return headerMap;
            }
        });
    }
}
