package com.mstring.andtest.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import static android.R.attr.data;

/**
 * Created by 李宗源 on 2016/10/9.
 */

public  abstract class BaseAdapter<T> extends RecyclerView.Adapter {

    public final LayoutInflater mInflater;
    public Context mContext;
    public ArrayList<T> mDatas;

    public BaseAdapter(Context context) {
        mDatas = new ArrayList<>();
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addAll(ArrayList<T> items) {
        if (items != null) {
            mDatas.addAll(items);
        }
    }

}
