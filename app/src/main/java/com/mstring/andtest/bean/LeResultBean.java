package com.mstring.andtest.bean;

import java.io.Serializable;
import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by 李宗源 on 2016/9/28.
 * 乐视接口bean
 */

public class LeResultBean<T> implements Serializable {
    private int code;//状态值：0表示操作成功；其它值表示失败，具体含义见message说明
    private String message;// 状态说明
    private int total;//符合条件的记录数量
    private T data;//返回具体信息

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LeResultBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
}
