package com.example.wanandroid.ui.homepage.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wanandroid.R;
import com.example.wanandroid.http.bean.Bean;
import com.example.wanandroid.ui.main.MainActivity;
import com.example.wanandroid.ui.web.WebActivity;
import com.example.wanandroid.ui.homepage.adapter.HomeRvAdapter;
import com.example.wanandroid.app.App;
import com.example.wanandroid.base.BaseFragment;

import com.example.wanandroid.http.bean.HomeBannerBean;
import com.example.wanandroid.http.bean.HomeListBean;
import com.example.wanandroid.presenter.HomePresenter;
import com.example.wanandroid.util.DaoUtil;
import com.example.wanandroid.util.Logger;
import com.example.wanandroid.util.ToastUtil;
import com.example.wanandroid.view.HomeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by 张嘉河 on 2019/4/28.
 */

public class HomePageFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {

    private static final String TAG = "HomePageFragment";
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    private HomeRvAdapter mAdapter;
    private int mPage = 0;


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
//        mSmart.setRefreshHeader(new PhoenixHeader(App.getInstance()));
        mRv.setLayoutManager(new LinearLayoutManager(App.getInstance()));
        mAdapter = new HomeRvAdapter(getActivity(), new ArrayList<HomeBannerBean.DataBean>(), new ArrayList<HomeListBean.DataBean.DatasBean>());
        mRv.setAdapter(mAdapter);

        HideTablayout_Float();
    }

    @Override
    protected void initListener() {
        mSmart.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                mPresenter.initList(mPage);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 0;
                mAdapter.mBannerBeans.clear();
                mAdapter.mList.clear();
                initData();
            }
        });

        mAdapter.setOnClickListener(new HomeRvAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("niceDate", mAdapter.mList.get(position).getNiceDate());
                intent.putExtra("author", mAdapter.mList.get(position).getAuthor());
                intent.putExtra("superChapterName", mAdapter.mList.get(position).getSuperChapterName());
                intent.putExtra("chapterName", mAdapter.mList.get(position).getChapterName());
                intent.putExtra("id", mAdapter.mList.get(position).getId());
                intent.putExtra("title", mAdapter.mList.get(position).getTitle());
                intent.putExtra("url", mAdapter.mList.get(position).getLink());
                startActivityForResult(intent, 100);
            }
        });

    }

    private void HideTablayout_Float() {

        MainActivity activity = (MainActivity) getActivity();
        //滑动recyclerView隐藏tablayout
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItem != 0) {
                    if (dy > 15 ) {
                        activity.getFlaBut().setVisibility(View.GONE);
                        activity.getRadioGroup().setVisibility(View.GONE);
                    } else if (dy < -15 ) {
                        activity.getFlaBut().setVisibility(View.VISIBLE);
                        activity.getRadioGroup().setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        activity.getFlaBut().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mRv.smoothScrollToPosition(0);
                return true;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            boolean isBoolean = data.getBooleanExtra("isBoolean", true);
            Log.d(TAG, "isBoolean: " + isBoolean);
            if (isBoolean) {
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void initData() {
        mPresenter.initBanner();
        mPresenter.initList(mPage);
    }

    @Override
    public void onSuccessBanner(HomeBannerBean homeBannerBean) {
        mAdapter.setBanner(homeBannerBean.getData());
        Logger.logD("HomePageFragment", homeBannerBean.toString());
        finishRefreshAndLoadmore();
    }

    private void finishRefreshAndLoadmore() {
        mSmart.finishRefresh();
        mSmart.finishLoadmore();
    }

    @Override
    public void onSuccessArticle(HomeListBean homeListBean) {
        mAdapter.setList(homeListBean.getData().getDatas());
        finishRefreshAndLoadmore();
        Logger.logD("HomePageFragment", homeListBean.toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity) getActivity();
        boolean isBoolean = activity.mIsBoolean;
        Log.d(TAG, "mIsBoolean: "+isBoolean);
        if (isBoolean){
            mAdapter.notifyDataSetChanged();
        }
        Log.d(TAG, "onResume: ");
    }
}
