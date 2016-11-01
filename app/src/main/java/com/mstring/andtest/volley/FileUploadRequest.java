package com.mstring.andtest.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.mstring.andtest.utils.TLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 李宗源 on 2016/10/28.
 */

public class FileUploadRequest extends Request<String> {
    private final Listener<String> mListener;

    private FileUploadEntity mFileUploadEntity = new FileUploadEntity();
    private Map<String, String> mHeaders = new HashMap<>();


    public FileUploadRequest(String url, Listener<String> listener) {
        this(url, listener, null);
    }

    public FileUploadRequest(String url, Listener<String> listener, ErrorListener errorListener) {
        this(Method.POST, url, listener, errorListener);
    }

    public FileUploadRequest(int method, String url, Listener<String> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
    }

    public FileUploadEntity getFileUploadEntity() {
        return mFileUploadEntity;
    }

    @Override
    public String getBodyContentType() {
        return mFileUploadEntity.getContentType().getValue();
    }

    public void addHeader(String key, String value) {
        mHeaders.put(key, value);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        TLog.d("zyzd", "FileUploadRequest>>getHeaders--> " );
        return mHeaders;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        byte[] bytes = super.getBody();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


        try {
            outputStream.write(bytes);
            mFileUploadEntity.writeTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed = "";
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }
}
