package com.mstring.andtest.base;

import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mstring.andtest.R;
import com.mstring.andtest.bean.LeResultBean;
import com.mstring.andtest.utils.HeaderAndFooterViewUtil;
import com.mstring.andtest.utils.TLog;
import com.mstring.andtest.utils.VolleyHelper;
import com.mstring.andtest.view.LoadMoreView;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import cn.iwgang.familiarrecyclerview.FamiliarRefreshRecyclerView;

public abstract class ImitateGridViewDemoActivity<T> extends BaseActivity implements FamiliarRecyclerView.OnItemClickListener
        , FamiliarRecyclerView.OnItemLongClickListener, FamiliarRefreshRecyclerView.OnPullRefreshListener
        , FamiliarRefreshRecyclerView.OnLoadMoreListener {

    private FamiliarRefreshRecyclerView mCvRefreshGridRecyclerView;
    private FamiliarRecyclerView mFamiliarRecyclerView;

    private ArrayList<T> mDatas = new ArrayList<T>();//用于装载数据的集合;
    protected BaseAdapter<T> mAdapter;
    protected boolean mIsRefresh;
    private int mDefaultPageIndex = 1;
    protected int mPageIndex = mDefaultPageIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.act_layout_imitate_gridview;
    }

    @Override
    protected void initView() {
        super.initView();
        mCvRefreshGridRecyclerView = (FamiliarRefreshRecyclerView) findViewById(R.id.cv_refreshGridRecyclerView);
        mCvRefreshGridRecyclerView.setLoadMoreView(new LoadMoreView(this));//设置加载更多的条目
        mCvRefreshGridRecyclerView.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN);
        mCvRefreshGridRecyclerView.setLoadMoreEnabled(true);//是否可以加载更多

        mFamiliarRecyclerView = mCvRefreshGridRecyclerView.getFamiliarRecyclerView();
        // ItemAnimator
        mFamiliarRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置条目动画
        // head view
        //mFamiliarRecyclerView.addHeaderView(HeaderAndFooterViewUtil.getHeadView(this, true, 0xFFFF5000, "Head View 1"));//设置头部条目
    }

    @Override
    protected void addListener() {
        super.addListener();
        // Item Click and Item Long Click
        mCvRefreshGridRecyclerView.setOnItemClickListener(this);//设置条目点击事件
        mCvRefreshGridRecyclerView.setOnItemLongClickListener(this);//设置条目长按事件
        mCvRefreshGridRecyclerView.setOnPullRefreshListener(this);//设置下拉刷新
        mCvRefreshGridRecyclerView.setOnLoadMoreListener(this);//设置加载更多
    }

    @Override
    protected void initData() {
        super.initData();
        mAdapter = getAdapter();
        mCvRefreshGridRecyclerView.setAdapter(mAdapter);//给刷新列表设置适配器
        mCvRefreshGridRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                onPullRefresh();
            }
        });
    }

    protected abstract BaseAdapter<T> getAdapter();

    @Override
    public void onPullRefresh() {
        mIsRefresh = true;
        requestData();
    }

    /**
     * 请求数据
     */
    private void requestData() {
        if(mIsRefresh){
            mPageIndex = mDefaultPageIndex ;
        }else{
            mPageIndex++;
        }

        String url = getUrl(mIsRefresh,mPageIndex,getPageSize());
        TLog.d("zyzd", "BaseRecyclerViewFragment>>url--> " + url);
        VolleyHelper.stringRequestByGet(url, this, this);
    }

    @Override
    public void onResponse(String response) {
        onSuccess(response);
        mCvRefreshGridRecyclerView.pullRefreshComplete();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        mPageIndex--;//请求失败，需要对index进行回滚
        mCvRefreshGridRecyclerView.pullRefreshComplete();
    }

    private void onSuccess(String response) {
        try {
            LeResultBean<ArrayList<T>> resultBean = (LeResultBean<ArrayList<T>>) new Gson().fromJson(response, getType());
            if (resultBean == null || resultBean.getCode() != 0) {
                mPageIndex--;//回滚，具体还没构思很好，有待检验
                return;
            }
            if (resultBean.getData() == null) {
                mPageIndex--;//回滚，具体还没构思很好，有待检验
                return;
            }
            parseData(resultBean.getData());
        } catch (JsonSyntaxException e) {
            TLog.e("zyzd", e.toString());
        }
    }

    protected abstract Type getType();

    protected void parseData(ArrayList<T> data) {
        if (mIsRefresh) {
            mAdapter.mDatas.clear();
            mAdapter.mDatas.addAll(data);
            mAdapter.notifyDataSetChanged();
        } else {
            int startPos = mDatas.size();
            mAdapter.mDatas.addAll(data);
//            mAdapter.notifyItemInserted(startPos);
            mAdapter.notifyDataSetChanged();
        }

        if (data.size() < getPageSize()) {
            mCvRefreshGridRecyclerView.setLoadMoreEnabled(false);
        }else{
            mCvRefreshGridRecyclerView.setLoadMoreEnabled(true);
        }

        TLog.d("zyzd", "加载完成啦..");
    }


    protected abstract String getUrl(boolean isRefresh,int index,int size) ;
    protected abstract int getPageSize();

    @Override
    public void onLoadMore() {
        mIsRefresh = false;
        requestData();
    }

    /**
     * 条目点击事件
     *
     * @param familiarRecyclerView
     * @param view
     * @param position
     * @return
     */
    @Override
    public boolean onItemLongClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
        TLog.d("zyzd", "onItemLongClick = " + familiarRecyclerView + " _ " + view + " _ " + position);
        return true;
    }

    /**
     * 条目长按事件
     *
     * @param familiarRecyclerView
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
        TLog.d("zyzd", "onItemClick = " + familiarRecyclerView + " _ " + view + " _ " + position);
    }

    public void setmDefaultPageIndex(int defaultPageIndex) {
        this.mDefaultPageIndex = defaultPageIndex;
    }


    //    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {//对应适配器
//
//        @Override
//        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            return new MyViewHolder(LayoutInflater.from(ImitateGridViewDemoActivity.this).inflate(R.layout.item_view_grid, parent, false));
//        }
//
//        @Override
//        public void onBindViewHolder(MyViewHolder holder, int position) {
////            holder.mTvTxt.setText(mDatas.get(position));
//        }
//
//        @Override
//        public int getItemCount() {
//            return mDatas.size();
//        }
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView mTvTxt;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
////            mTvTxt = (TextView)itemView.findViewById(R.id.tv_txt);
//        }
//    }

}
