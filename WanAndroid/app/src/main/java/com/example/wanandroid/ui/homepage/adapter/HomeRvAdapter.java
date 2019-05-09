package com.example.wanandroid.ui.homepage.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wanandroid.R;

import com.example.wanandroid.http.bean.Bean;
import com.example.wanandroid.ui.web.Web2Activity;
import com.example.wanandroid.http.bean.HomeBannerBean;
import com.example.wanandroid.http.bean.HomeListBean;
import com.example.wanandroid.util.DaoUtil;
import com.example.wanandroid.util.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张嘉河 on 2019/4/29.
 */

public class HomeRvAdapter extends RecyclerView.Adapter {
    private static final String TAG = "HomeRvAdapter";
    private final Context mContext;
    public ArrayList<HomeBannerBean.DataBean> mBannerBeans;
    public ArrayList<HomeListBean.DataBean.DatasBean> mList;
    private final int BANNER_ITEM = 0;
    private final int LIST_ITEM = 1;
    private int mPosition;
    private ArrayList<String> mTitles;
    private OnClickListener mListener;

    public HomeRvAdapter(Context context, ArrayList<HomeBannerBean.DataBean> bannerBeans, ArrayList<HomeListBean.DataBean.DatasBean> list) {

        mContext = context;
        mBannerBeans = bannerBeans;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mTitles = new ArrayList<>();
        for (HomeBannerBean.DataBean bannerBean : mBannerBeans) {
            mTitles.add(bannerBean.getTitle());
            Log.d(TAG, "onCreateViewHolder: " + bannerBean.getTitle());
        }
        if (viewType == BANNER_ITEM) {
            return new BannerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.banner_item, null));
        } else {
            return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.main_item, null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == BANNER_ITEM) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.mBan.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
            bannerViewHolder.mBan.setBannerAnimation(Transformer.DepthPage);
            bannerViewHolder.mBan.setBannerTitles(mTitles);
            bannerViewHolder.mBan.setImages(mBannerBeans).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    HomeBannerBean.DataBean db = (HomeBannerBean.DataBean) path;
                    Glide.with(mContext).load(db.getImagePath()).into(imageView);

                }
            });
            bannerViewHolder.mBan.start();
            bannerViewHolder.mBan.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(mContext, Web2Activity.class);
                    intent.putExtra("url", mBannerBeans.get(position).getUrl());
                    intent.putExtra("title", mBannerBeans.get(position).getTitle());
                    mContext.startActivity(intent);
                }
            });
        } else {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            mPosition = position;
            if (mBannerBeans.size() > 0) {
                mPosition = position - 1;
            }
            HomeListBean.DataBean.DatasBean bean = mList.get(mPosition);
            itemViewHolder.mTvTitle.setText(bean.getTitle());
            itemViewHolder.mTvAuthor.setText(bean.getAuthor());
            itemViewHolder.mTvChapterName.setText(bean.getChapterName() + "/" + bean.getSuperChapterName());
            itemViewHolder.mTvTime.setText(bean.getNiceDate());

            if (bean.isFresh() !=true) {
                itemViewHolder.mNews.setVisibility(View.GONE);
            } else {
                itemViewHolder.mNews.setVisibility(View.VISIBLE);
            }

            if (bean.getTags().size() > 0) {
                itemViewHolder.mTvNavigation.setVisibility(View.VISIBLE);
            } else {
                itemViewHolder.mTvNavigation.setVisibility(View.GONE);
            }

            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(v, (position-1));
                }
            });

            select(itemViewHolder, (position - 1));//查询数据库

            itemViewHolder.mIvLove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insert((position - 1), itemViewHolder.mIvLove);
                }
            });
        }
    }

    private void select(ItemViewHolder itemViewHolder, int position) {
        //上来查询数据库
        HomeListBean.DataBean.DatasBean bean = mList.get(position);
        List<Bean> query = DaoUtil.getBaseUtil().query();
        itemViewHolder.mIvLove.setImageResource(R.mipmap.like_seleced);
        for (int i = 0; i < query.size(); i++) {
            String link = query.get(i).getLink();
            if (link.equals(bean.getLink())) {
                itemViewHolder.mIvLove.setImageResource(R.mipmap.love_seleced);
            }
        }
    }

    private void insert(int position, ImageView ivLove) {
        HomeListBean.DataBean.DatasBean datasBean = mList.get(position);
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
        if (mBannerBeans.size() > 0) {
            return mList.size() + 1;
        } else {
            return mList.size();
        }
    }

    public void setBanner(List<HomeBannerBean.DataBean> homeBannerBean) {
        mBannerBeans.addAll(homeBannerBean);
        notifyDataSetChanged();
    }

    public void setList(List<HomeListBean.DataBean.DatasBean> datas) {
        mList.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (position == BANNER_ITEM) {
            return BANNER_ITEM;
        } else {
            return LIST_ITEM;
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        Banner mBan;

        public BannerViewHolder(View itemView) {
            super(itemView);
            this.mBan = (Banner) itemView.findViewById(R.id.ban);
        }
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
