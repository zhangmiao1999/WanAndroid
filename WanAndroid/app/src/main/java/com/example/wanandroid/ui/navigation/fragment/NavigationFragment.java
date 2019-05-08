package com.example.wanandroid.ui.navigation.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.http.bean.NavigationBean;
import com.example.wanandroid.presenter.NavigationPresenter;
import com.example.wanandroid.ui.main.MainActivity;
import com.example.wanandroid.ui.navigation.adapter.NavigationRvAdapter;
import com.example.wanandroid.ui.navigation.adapter.TabAdapter;
import com.example.wanandroid.view.NavigationView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.TabView;


/**
 * Created by 张嘉河 on 2019/4/28.
 */

public class NavigationFragment extends BaseFragment<NavigationView, NavigationPresenter> implements NavigationView {

    private static final String TAG = "NavigationFragment";
    @BindView(R.id.verticalTabLayout)
    VerticalTabLayout mVerticalTabLayout;
    @BindView(R.id.navigation_divider)
    View mNavigationDivider;
    @BindView(R.id.navigation_RecyclerView)
    RecyclerView mNavigationRecyclerView;
    private NavigationRvAdapter mAdapter;
    private boolean isClick = false;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected NavigationPresenter createPresenter() {
        return new NavigationPresenter();
    }


    @Override
    protected void initView() {
        mNavigationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NavigationRvAdapter(getContext(), new ArrayList<NavigationBean.DataBean>());
        mNavigationRecyclerView.setAdapter(mAdapter);
HideTablayout_Float();
    }
    private void HideTablayout_Float() {

        final MainActivity mainActivity = (MainActivity) getActivity();
        //滑动recyclerView隐藏tablayout
        mNavigationRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
    protected void initData() {
        mPresenter.initData();
    }

    @Override
    protected void initListener() {
        mVerticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {

            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });

    }

    @Override
    public void onSuccess(NavigationBean bean) {
        List<NavigationBean.DataBean> data = bean.getData();
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String name = data.get(i).getName();
            Log.d(TAG, "name: " + name);
            titles.add(name);
        }
        TabAdapter adapter = new TabAdapter(titles, getContext());
        mVerticalTabLayout.setTabAdapter(adapter);
        Log.d(TAG, "title: " + titles);
        mAdapter.setData(data);

        mVerticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) mNavigationRecyclerView.getLayoutManager();
                layoutManager.scrollToPositionWithOffset(position, 0);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });

        mNavigationRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //重写该方法主要是判断recyclerview是否在滑动
                //0停止 ，1,2都是滑动
                if (newState == 0) {
                    isClick = false;
                } else {
                    isClick = true;
                }
                LinearLayoutManager layoutManager = (LinearLayoutManager) mNavigationRecyclerView.getLayoutManager();
                //可见的第一条条目
                int top = layoutManager.findFirstVisibleItemPosition();
                mVerticalTabLayout.setTabSelected(top);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //这个主要是recyclerview滑动时让tab定位的方法

       /*recyclerView : 当前滚动的view
        dx : 水平滚动距离
        dy : 垂直滚动距离
        dx > 0 时为手指向左滚动,列表滚动显示右面的内容
        dx < 0 时为手指向右滚动,列表滚动显示左面的内容
        dy > 0 时为手指向上滚动,列表滚动显示下面的内容
        dy < 0 时为手指向下滚动,列表滚动显示上面的内容*/
                LinearLayoutManager layoutManager = (LinearLayoutManager) mNavigationRecyclerView.getLayoutManager();
                //可见的第一条条目
                int top = layoutManager.findFirstVisibleItemPosition();
                //可见的最后一条条目
                int bottom = layoutManager.findLastVisibleItemPosition();
                if (isClick) {
                    if (dy > 0) {
                        mVerticalTabLayout.setTabSelected(top);
                    }
                }
            }
        });
    }

}
