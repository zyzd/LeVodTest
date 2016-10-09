package com.mstring.andtest.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by 李宗源 on 2016/10/8.
 */

public class BaseRecyclerAdapter<T> extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
