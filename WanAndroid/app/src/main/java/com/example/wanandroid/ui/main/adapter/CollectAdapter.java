package com.example.wanandroid.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.http.bean.Bean;
import com.example.wanandroid.util.DaoUtil;
import com.example.wanandroid.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张嘉河 on 2019/5/6.
 */

public class CollectAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    public ArrayList<Bean> mBeans;
    private OnClickListener mListener;
    private IvOnClickListener mIvOnClickListener;
    public ImageView mIvLove;

    public CollectAdapter(Context context, ArrayList<Bean> beans) {

        mContext = context;
        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.main_item, null);
        return new ItemViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        Bean bean = mBeans.get(position);
        itemViewHolder.mTvTitle.setText(bean.getTitle());
        itemViewHolder.mTvAuthor.setText(bean.getAuthor());
        itemViewHolder.mTvChapterName.setText(bean.getCharName() + "/" + bean.getCharSuperName());
        itemViewHolder.mTvTime.setText(bean.getNiceDate());

        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(v, position);
            }
        });
        mIvLove = itemViewHolder.mIvLove;
        itemViewHolder.mIvLove.setImageResource(R.mipmap.love_seleced);
        itemViewHolder.mIvLove.setOnClickListener(v -> onClick(position));
    }

    private void onClick(int position) {
        mIvOnClickListener.ivOnClick(position);
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void setData(List<Bean> bean) {
        mBeans.addAll(bean);
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView mTvAuthor;
        TextView mTvChapterName;
        TextView mTvTitle;
        ImageView mIvLove;
        TextView mTvTime;
        TextView mTvNavigation;
        TextView mNews;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.mTvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            this.mTvChapterName = (TextView) itemView.findViewById(R.id.tv_chapterName);
            this.mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            this.mIvLove = (ImageView) itemView.findViewById(R.id.iv_love);
            this.mTvTime = (TextView) itemView.findViewById(R.id.tv_time);
            this.mTvNavigation = (TextView) itemView.findViewById(R.id.tv_navigation);
            this.mNews = (TextView) itemView.findViewById(R.id.news);
        }
    }

    public interface OnClickListener {
        void onClick(View view, int position);
    }

    public void setOnClickListener(OnClickListener listener) {

        mListener = listener;
    }

    public interface IvOnClickListener{
        void ivOnClick(int position);
    }
    public void setIvOnClickListener(IvOnClickListener ivOnClickListener){

        mIvOnClickListener = ivOnClickListener;
    }


}
