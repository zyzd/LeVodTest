package com.mstring.andtest.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mstring.andtest.ApiApplication;
import com.mstring.andtest.volley.FileUploadEntity;
import com.mstring.andtest.volley.FileUploadRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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

    public static void stringRequestByPost(String url, final Map<String, String> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        addRequestQueue(new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                TreeMap<String, String> headerMap = new TreeMap<>();
                headerMap.put("Content-Type", "application/x-www-form-urlencoded");
                headerMap.put("charset", "utf-8");
                return headerMap;
            }

        });
    }

    public static void modifyCoverByPost(String url, final Map<String, String> params, Response.Listener<String> listener, Response.ErrorListener errorListener, File file) {
        FileUploadRequest fileUploadRequest = new FileUploadRequest(Request.Method.POST, url, listener, errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                TLog.d("zyzd", "VolleyHelper>>getParams--> ");
                return params;
            }
        };


        fileUploadRequest.addHeader("Content-Type","application/x-www-form-urlencoded");
        fileUploadRequest.addHeader("charset","utf-8");

        FileUploadEntity fileUploadEntity = fileUploadRequest.getFileUploadEntity();
        fileUploadEntity.addFile("file",file);

        addRequestQueue(fileUploadRequest);

    }



//    public static void modifyCoverByPost(String url, final Map<String, String> params, Response.Listener<String> listener, Response.ErrorListener errorListener, final byte[] bytes) {
//        addRequestQueue(new StringRequest(Request.Method.POST, url, listener, errorListener) {
//            private String BOUNDARY = "--------------520-13-14"; //数据分隔线
//            private String MULTIPART_FORM_DATA = "multipart/form-data";
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                TLog.d("zyzd", "VolleyHelper>>getParams--> " + params);
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                TLog.d("zyzd", "VolleyHelper>>getHeaders--> ");
//                TreeMap<String, String> headerMap = new TreeMap<>();
//                headerMap.put("Content-Type", "application/x-www-form-urlencoded");
//                headerMap.put("charset", "utf-8");
//                return headerMap;
//            }
//
//            @Override
//            public byte[] getBody() throws AuthFailureError {
//
//                String PREFIX = "--", LINEND = "\r\n";
//                String BOUNDARY = java.util.UUID.randomUUID().toString();
//                String CHARSET = "UTF-8";
//
//
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                StringBuffer sb = new StringBuffer();
//                try {
//
//                    for (Map.Entry<String, String> entry : params.entrySet()) {
//                        sb.append(PREFIX);
//                        sb.append(BOUNDARY);
//                        sb.append(LINEND);
//                        sb.append("Content-Disposition: form-data; name=\""
//                                + entry.getKey() + "\"" + LINEND);
//                        sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
//                        sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
//                        sb.append(LINEND);
//                        sb.append(entry.getValue());
//                        sb.append(LINEND);
//                    }
//
//                    bos.write(sb.toString().getBytes(CHARSET));
//
//                    StringBuffer buffer = new StringBuffer();
//                    buffer.append(PREFIX);
//                    buffer.append(BOUNDARY);
//                    buffer.append(LINEND);
//                    buffer.append("Content-Disposition: form-data; " + LINEND);
//                    buffer.append("Content-Type: application/octet-stream; charset="
//                            + CHARSET + LINEND);
//                    buffer.append(LINEND);
//
//                    bos.write(buffer.toString().getBytes(CHARSET));
//                    bos.write(bytes);
////                    String boundary = "---------------------------265001916915724";
////                    String lineEnd = "\r\n";
////
//
////                    //开始伪造上传图片的信息
////                    String fileMeta = "--"
////                            + boundary
////                            + lineEnd
////                            + "Content-Disposition: form-data; name=\"uploadedPicture\"; filename=\""
////                            + "photo" + "\"" + lineEnd + "Content-Type: image/jpeg"
////                            + lineEnd + lineEnd;
////
////                    // 向流中写入fileMeta
////                    bos.write(fileMeta.getBytes("utf-8"));
////                    //写入图片的流
////                    bos.write(bytes);
////                    bos.write((lineEnd + lineEnd).getBytes("utf-8"));
////                    bos.write(("--" + boundary + "--").getBytes("utf-8"));
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return bos.toByteArray();
//            }
//        });
//    }


}