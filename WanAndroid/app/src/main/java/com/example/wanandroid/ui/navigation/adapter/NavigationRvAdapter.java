package com.example.wanandroid.ui.navigation.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.http.bean.NavigationBean;
import com.example.wanandroid.ui.web.WebActivity;
import com.example.wanandroid.util.ColorUtil;
import com.example.wanandroid.widget.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张嘉河 on 2019/5/5.
 */

public class NavigationRvAdapter extends RecyclerView.Adapter {
    private static final String TAG = "NavigationRvAdapter";
    private final Context mContext;
    public ArrayList<NavigationBean.DataBean> mBeans;
    private OnClickLisenter mLisenter;

    public NavigationRvAdapter(Context context, ArrayList<NavigationBean.DataBean> beans) {

        mContext = context;
        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.navigation_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mItemNavigationFlowLayout.removeAllViews();
        viewHolder.mItemNavigationTv.setText(mBeans.get(position).getName());
        NavigationBean.DataBean dataBean = mBeans.get(position);
        for (int j = 0; j < mBeans.get(position).getArticles().size(); j++) {
            TextView lable = (TextView) LayoutInflater.from(mContext).inflate(R.layout.navigation_item_lable, null);
            lable.setTextColor(Color.parseColor(ColorUtil.getRandomColor()));
            String title = mBeans.get(position).getArticles().get(j).getTitle();
            lable.setText(title);
            int finalJ = j;
            lable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("niceDate", dataBean.getArticles().get(finalJ).getNiceDate());
                    intent.putExtra("author", dataBean.getArticles().get(finalJ).getAuthor());
                    intent.putExtra("superChapterName", dataBean.getArticles().get(finalJ).getSuperChapterName());
                    intent.putExtra("chapterName", dataBean.getArticles().get(finalJ).getChapterName());
                    intent.putExtra("id", dataBean.getArticles().get(finalJ).getId());
                    intent.putExtra("url", dataBean.getArticles().get(finalJ).getLink());
                    intent.putExtra("title", dataBean.getArticles().get(finalJ).getTitle());
                    mContext.startActivity(intent);
                }
            });
            Log.i(TAG, "into: " + title);

            viewHolder.mItemNavigationFlowLayout.addView(lable);
        }
    }


    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void setData(List<NavigationBean.DataBean> data) {
        mBeans.addAll(data);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mItemNavigationTv;
        FlowLayout mItemNavigationFlowLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mItemNavigationTv = (TextView) itemView.findViewById(R.id.item_navigation_tv);
            this.mItemNavigationFlowLayout = (FlowLayout) itemView.findViewById(R.id.item_navigation_flow_layout);
        }
    }

    public interface OnClickLisenter {
        void onClick(int position);
    }

    public void setOnClickLisenter(OnClickLisenter lisenter) {
        mLisenter = lisenter;
    }
}
