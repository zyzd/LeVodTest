package com.mstring.andtest.bean;

import java.io.Serializable;

/**
 * Created by 李宗源 on 2016/10/31.
 */

public class LeLiveGetUrlBean implements Serializable {
    private String playPageUrl;

    public String getPlayPageUrl() {
        return playPageUrl;
    }

    public void setPlayPageUrl(String playPageUrl) {
        this.playPageUrl = playPageUrl;
    }

    @Override
    public String toString() {
        return "LeLiveGetUrlBean{" +
                "playPageUrl='" + playPageUrl + '\'' +
                '}';
    }
}
