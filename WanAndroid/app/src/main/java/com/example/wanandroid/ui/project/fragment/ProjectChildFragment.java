package com.example.wanandroid.ui.project.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.web.Web2Activity;
import com.example.wanandroid.ui.project.adapter.ProjectChildAdapter;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.http.bean.ProjectListBean;
import com.example.wanandroid.presenter.ProjectChildPresenter;
import com.example.wanandroid.util.Logger;
import com.example.wanandroid.view.ProjectChildView;
import com.scwang.smartrefresh.header.FunGameBattleCityHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 张嘉河 on 2019/4/30.
 */

public class ProjectChildFragment extends BaseFragment<ProjectChildView, ProjectChildPresenter> implements ProjectChildView {

    private static final String TAG = "ProjectChildFragment";
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    private ProjectChildAdapter mAdapter;
    private int mCid;

    @Override
    protected ProjectChildPresenter createPresenter() {
        return new ProjectChildPresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_project_child;
    }

    @Override
    protected void initView() {
        getCid();
        mSmart.setRefreshHeader(new FunGameBattleCityHeader(getContext()));
        mSmart.setEnableLoadmore(false);//是否启用上拉加载功能
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ProjectChildAdapter(new ArrayList<ProjectListBean.DataBean.DatasBean>(),getContext());
        mRv.setAdapter(mAdapter);
    }

    private void getCid() {
        mCid = getArguments().getInt("cid");
        Log.d(TAG, "getCid: "+mCid);
    }

    @Override
    protected void initListener() {
        mSmart.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        mAdapter.setOnClickListener(new ProjectChildAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), Web2Activity.class);
                intent.putExtra("url",mAdapter.mBeans.get(position).getLink());
                intent.putExtra("title",mAdapter.mBeans.get(position).getTitle());
                startActivity(intent);
            }
        });
        mAdapter.setOnClickListener(new ProjectChildAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(),Web2Activity.class);
                intent.putExtra("url",mAdapter.mBeans.get(position).getLink());
                intent.putExtra("title",mAdapter.mBeans.get(position).getTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.initData(mCid);
    }

    @Override
    public void onSuccess(ProjectListBean bean) {
        mAdapter.setData(bean);
        Logger.logD("ProjectChildFragment",bean.toString());
    }
}