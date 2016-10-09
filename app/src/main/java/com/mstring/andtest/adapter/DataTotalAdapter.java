package com.mstring.andtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseAdapter;
import com.mstring.andtest.bean.LeDataTotalDateBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李宗源 on 2016/10/8.
 */

public class DataTotalAdapter extends BaseAdapter<LeDataTotalDateBean> {

    public DataTotalAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataTotalViewHolder(mInflater.inflate(R.layout.item_data_total, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final DataTotalViewHolder h = (DataTotalViewHolder) holder;
        h.tvDate.setText(mDatas.get(position).getDate());
        h.tvTotalView.setText(mDatas.get(position).getTotal_view());
    }

    static class DataTotalViewHolder extends RecyclerView.ViewHolder  {
        TextView tvDate;
        TextView tvTotalView;

        public DataTotalViewHolder(View view) {
            super(view);
            tvDate = (TextView) view.findViewById(R.id.tv_date);
            tvTotalView = (TextView) view.findViewById(R.id.tv_total_view);
        }
    }
}
