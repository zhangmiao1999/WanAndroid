package com.example.wanandroid.ui.knowledge.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.example.wanandroid.R;

import com.example.wanandroid.ui.knowledge.activity.KnowledgeActivity;
import com.example.wanandroid.ui.knowledge.adapter.KnowledgeRvAdapter;
import com.example.wanandroid.base.BaseFragment;

import com.example.wanandroid.http.bean.KnowledgeBean;
import com.example.wanandroid.presenter.KnowledgePresenter;
import com.example.wanandroid.ui.main.MainActivity;
import com.example.wanandroid.util.Logger;
import com.example.wanandroid.util.ToastUtil;
import com.example.wanandroid.view.KnowledgeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * Created by 张嘉河 on 2019/4/28.
 */

public class KnowledgeFragment extends BaseFragment<KnowledgeView, KnowledgePresenter> implements KnowledgeView {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    private KnowledgeRvAdapter mAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_knowledge;
    }


    @Override
    protected void initView() {
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new KnowledgeRvAdapter(new ArrayList<KnowledgeBean.DataBean>(),getContext());
        mRv.setAdapter(mAdapter);
        HideTablayout_Float();
    }
    private void HideTablayout_Float() {

        final MainActivity mainActivity = (MainActivity) getActivity();
        //滑动recyclerView隐藏tablayout
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItem != 0) {
                    if (dy > 15 ) {
                        mainActivity.getFlaBut().setVisibility(View.GONE);
                        mainActivity.getRadioGroup().setVisibility(View.GONE);
                    } else if (dy < -15 ) {
                        mainActivity.getFlaBut().setVisibility(View.VISIBLE);
                        mainActivity.getRadioGroup().setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }

    @Override
    protected KnowledgePresenter createPresenter() {
        return new KnowledgePresenter();
    }


    @Override
    protected void initData() {
        mPresenter.initData();
    }

    @Override
    protected void initListener() {
        mSmart.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore();
                ToastUtil.showShort("没有更多干货了");
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
            }
        });

        mAdapter.setOnClickListener(new KnowledgeRvAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), KnowledgeActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("bean",mAdapter.mBeans);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess(KnowledgeBean bean) {
        mAdapter.setData(bean.getData());
        Logger.logD("KnowledgeFragment",bean.toString());
    }
}
