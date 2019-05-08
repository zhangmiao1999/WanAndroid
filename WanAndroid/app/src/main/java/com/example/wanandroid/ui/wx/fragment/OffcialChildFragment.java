package com.example.wanandroid.ui.wx.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.http.bean.OffcialListBean;
import com.example.wanandroid.http.bean.WxWeekBean;
import com.example.wanandroid.presenter.OffcialChildPresenter;
import com.example.wanandroid.ui.main.MainActivity;
import com.example.wanandroid.ui.web.WebActivity;
import com.example.wanandroid.ui.wx.adapter.OffcialRvAdapter;
import com.example.wanandroid.view.OffcialChildView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张嘉河 on 2019/5/3.
 */

public class OffcialChildFragment extends BaseFragment<OffcialChildView, OffcialChildPresenter> implements OffcialChildView {
    private static final String TAG = "OffcialChildFragment";
    @BindView(R.id.searchView)
    SearchView mSearchView;
    @BindView(R.id.week_but)
    Button mWeekBut;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;

    private String mName;
    private int mId;
    private OffcialRvAdapter mAdapter;
    private int mPage = 1;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_official_child;
    }

    @Override
    protected OffcialChildPresenter createPresenter() {
        return new OffcialChildPresenter();
    }

    @Override
    protected void initView() {
        getArgument();
        mSearchView.setQueryHint(mName + "带你发现更多干货");
        mSmart.setRefreshFooter(new BallPulseFooter(getContext()));
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new OffcialRvAdapter(getContext(), new ArrayList<OffcialListBean.DataBean.DatasBean>());
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

    private void getArgument() {
        mName = getArguments().getString("name");
        mId = getArguments().getInt("id");
    }

    @Override
    protected void initData() {
        mPresenter.initData(mPage, mId);
    }

    /**设置SearchView下划线透明**/
    private void setUnderLinetransparent(SearchView searchView){
        try {
            Class<?> argClass = searchView.getClass();
            // mSearchPlate是SearchView父布局的名字
            Field ownField = argClass.getDeclaredField("rrrr");
            ownField.setAccessible(true);
            View mView = (View) ownField.get(searchView);
            mView.setBackgroundColor(Color.GREEN);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initListener() {
        /***
         * 设置搜索监听
         */
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /***
             *  //当点击搜索按钮时触发该方法
             * @param query
             * @return
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "query: "+query);
                return false;
            }

            /**
             * //当搜索内容改变时触发该方法
             * @param newText
             * @return
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "newText: "+newText);
                return false;
            }
        });

        mSmart.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                initData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mAdapter.mList.clear();
                initData();
            }
        });

        mAdapter.setOnClickListener(new OffcialRvAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("niceDate", mAdapter.mList.get(position).getNiceDate());
                intent.putExtra("author", mAdapter.mList.get(position).getAuthor());
                intent.putExtra("superChapterName", mAdapter.mList.get(position).getSuperChapterName());
                intent.putExtra("chapterName", mAdapter.mList.get(position).getChapterName());
                intent.putExtra("id", mAdapter.mList.get(position).getId());
                intent.putExtra("url", mAdapter.mList.get(position).getLink());
                intent.putExtra("title", mAdapter.mList.get(position).getTitle());
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
    public void onSuccess(OffcialListBean bean) {
        mAdapter.setData(bean.getData().getDatas());
        mSmart.finishRefresh();
        mSmart.finishLoadmore();
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

