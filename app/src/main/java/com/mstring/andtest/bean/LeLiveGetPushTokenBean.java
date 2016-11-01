package com.mstring.andtest.bean;

import java.io.Serializable;

/**
 * Created by 李宗源 on 2016/10/31.
 */

public class LeLiveGetPushTokenBean implements Serializable {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LeLiveGetPushTokenBean{" +
                "token='" + token + '\'' +
                '}';
    }
}
