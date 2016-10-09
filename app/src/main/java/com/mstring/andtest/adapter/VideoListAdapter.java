package com.mstring.andtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mstring.andtest.R;
import com.mstring.andtest.base.BaseAdapter;
import com.mstring.andtest.bean.LeVideoGetBean;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 李宗源 on 2016/10/9.
 */

public class VideoListAdapter extends BaseAdapter<LeVideoGetBean> {
    public VideoListAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoListViewHolder(mInflater.inflate(R.layout.item_video_panel, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VideoListViewHolder h = (VideoListViewHolder) holder;
        Glide.with(mContext).load(mDatas.get(position).getImg()).into(h.imageVideo);
        h.tvVideoName.setText(mDatas.get(position).getVideo_name());
        h.tvVideoId.setText(mDatas.get(position).getVideo_id()+"");
    }

    static class VideoListViewHolder extends RecyclerView.ViewHolder {
        ImageView imageVideo;
        TextView tvVideoName;
        TextView tvVideoId;

        VideoListViewHolder(View view) {
            super(view);
            imageVideo = (ImageView) view.findViewById(R.id.image_video);
            tvVideoName = (TextView) view.findViewById(R.id.tv_video_name);
            tvVideoId = (TextView) view.findViewById(R.id.tv_video_id);
        }
    }
}
