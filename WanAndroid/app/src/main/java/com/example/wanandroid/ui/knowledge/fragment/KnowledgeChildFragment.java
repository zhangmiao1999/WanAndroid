package com.example.wanandroid.ui.knowledge.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.http.bean.KnowledgeChildBean;
import com.example.wanandroid.presenter.KnowledgeChildPresenter;
import com.example.wanandroid.ui.knowledge.adapter.KnowledgeChildRvAdapter;
import com.example.wanandroid.ui.main.MainActivity;
import com.example.wanandroid.ui.web.WebActivity;
import com.example.wanandroid.util.ToastUtil;
import com.example.wanandroid.view.KnowledgeChildView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张嘉河 on 2019/5/4.
 */

public class KnowledgeChildFragment extends BaseFragment<KnowledgeChildView, KnowledgeChildPresenter> implements KnowledgeChildView {
    private static final String TAG = "KnowledgeChildFragment";
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    private KnowledgeChildRvAdapter mAdapter;
    private int mPage = 0;
    private int mId;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_knowledge_child;
    }

    @Override
    protected KnowledgeChildPresenter createPresenter() {
        return new KnowledgeChildPresenter();
    }


    @Override
    protected void initView() {
        mId = getArguments().getInt("id");
        Log.d(TAG, "传过来的id: " + mId);

        mSmart.setRefreshFooter(new BallPulseFooter(getContext()));

        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new KnowledgeChildRvAdapter(getContext(), new ArrayList<KnowledgeChildBean.DataBean.DatasBean>());
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.initData(mPage, mId);
    }

    @Override
    protected void initListener() {
        mSmart.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                initData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 0;
                mAdapter.mBeans.clear();
                initData();
            }
        });

        mAdapter.setOnClickListener(new KnowledgeChildRvAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("niceDate", mAdapter.mBeans.get(position).getNiceDate());
                intent.putExtra("author", mAdapter.mBeans.get(position).getAuthor());
                intent.putExtra("superChapterName", mAdapter.mBeans.get(position).getSuperChapterName());
                intent.putExtra("chapterName", mAdapter.mBeans.get(position).getChapterName());
                intent.putExtra("id", mAdapter.mBeans.get(position).getId());
                intent.putExtra("url", mAdapter.mBeans.get(position).getLink());
                intent.putExtra("title", mAdapter.mBeans.get(position).getTitle());
                startActivityForResult(intent, 100);
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
    public void onSuccess(KnowledgeChildBean bean) {
        mAdapter.setData(bean.getData().getDatas());
        if (bean.getData().getDatas() != null && bean.getData().getDatas().size() == 0) {
            ToastUtil.showShort("没有更多干货了");
            mSmart.finishLoadmore();
        }
        mSmart.finishLoadmore();
        mSmart.finishRefresh();
    }


}
