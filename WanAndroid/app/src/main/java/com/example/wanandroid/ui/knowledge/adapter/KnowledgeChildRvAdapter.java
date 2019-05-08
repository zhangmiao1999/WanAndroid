package com.example.wanandroid.ui.knowledge.adapter;

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
import com.example.wanandroid.http.bean.HomeListBean;
import com.example.wanandroid.http.bean.KnowledgeChildBean;
import com.example.wanandroid.util.DaoUtil;
import com.example.wanandroid.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张嘉河 on 2019/5/4.
 */

public class KnowledgeChildRvAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    public ArrayList<KnowledgeChildBean.DataBean.DatasBean> mBeans;
    private OnClickListener mListener;

    public KnowledgeChildRvAdapter(Context context, ArrayList<KnowledgeChildBean.DataBean.DatasBean> beans) {
        mContext = context;
        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.knowledge_item_child, null);
        return new ItemViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        KnowledgeChildBean.DataBean.DatasBean bean = mBeans.get(position);
        itemViewHolder.mTvTitle.setText(bean.getTitle());
        itemViewHolder.mTvAuthor.setText(bean.getAuthor());
        itemViewHolder.mTvChapterName.setText(bean.getSuperChapterName() + "/" + bean.getChapterName());
        itemViewHolder.mTvTime.setText(bean.getNiceDate());
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(v,position);
            }
        });

        select(itemViewHolder, bean);//查询数据库

        itemViewHolder.mIvLove.setOnClickListener(v -> onClick(itemViewHolder.mIvLove,position));
    }

    private void select(ItemViewHolder itemViewHolder, KnowledgeChildBean.DataBean.DatasBean bean) {
        //上来查询数据库
        List<Bean> query = DaoUtil.getBaseUtil().query();
        itemViewHolder.mIvLove.setImageResource(R.mipmap.like_seleced);
        for (int i = 0; i < query.size(); i++) {
            String link = query.get(i).getLink();
            if (link.equals(bean.getLink())) {
                itemViewHolder.mIvLove.setImageResource(R.mipmap.love_seleced);
            }
        }
    }


    private void onClick(ImageView ivLove, int position) {
        insert(ivLove,position);
    }

    private void insert(ImageView ivLove, int position) {
        KnowledgeChildBean.DataBean.DatasBean datasBean = mBeans.get(position);
        Bean daoBean = new Bean();
        daoBean.setAuthor(datasBean.getAuthor());
        daoBean.setCharName(datasBean.getChapterName());
        daoBean.setCharSuperName(datasBean.getSuperChapterName());
        daoBean.setId((long) datasBean.getId());
        daoBean.setLink(datasBean.getLink());
        daoBean.setNiceDate(datasBean.getNiceDate());
        daoBean.setTitle(datasBean.getTitle());

        collect(ivLove, daoBean);
    }

    private void collect(ImageView ivLove, Bean daoBean) {
        //插入数据库
        long insert = DaoUtil.getBaseUtil().insert(daoBean);
        //判断是否插入成功
        if (insert == -1) {
            boolean delete = DaoUtil.getBaseUtil().delete(daoBean);
            //代表数据库有，不能插入了
            if (delete) {
                ToastUtil.showShort("取消收藏");
                ivLove.setImageResource(R.mipmap.like_seleced);
            } else {
                ivLove.setImageResource(R.mipmap.love_seleced);
            }
        } else {
            //收藏成功 改成小红心
            ivLove.setImageResource(R.mipmap.love_seleced);
            ToastUtil.showShort("收藏成功");
        }
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void setData(List<KnowledgeChildBean.DataBean.DatasBean> datas) {
        mBeans.addAll(datas);
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

}
