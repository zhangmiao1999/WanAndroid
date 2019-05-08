package com.example.wanandroid.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.http.bean.Bean;
import com.example.wanandroid.presenter.CollectPresenter;
import com.example.wanandroid.ui.main.adapter.CollectAdapter;
import com.example.wanandroid.ui.web.WebActivity;
import com.example.wanandroid.util.DaoUtil;
import com.example.wanandroid.util.ToastUtil;
import com.example.wanandroid.view.CollectView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectActivity extends BaseActivity<CollectView, CollectPresenter> implements CollectView {

    private static final String TAG = "CollectActivity";
    @BindView(R.id.collect_rv)
    RecyclerView mCollectRv;
    @BindView(R.id.about_us_toolbar)
    Toolbar mAboutUsToolbar;
    private CollectAdapter mAdapter;
    private boolean isBoolean;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_collect;
    }

    @Override
    protected CollectPresenter createPresenter() {
        return new CollectPresenter();
    }

    @Override
    protected void initView() {
        mCollectRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CollectAdapter(this, new ArrayList<Bean>());
        mCollectRv.setAdapter(mAdapter);
        setSupportActionBar(mAboutUsToolbar);
        mAboutUsToolbar.setNavigationOnClickListener(v -> onBack());
    }

    private void onBack() {
        Intent intent = new Intent();
        intent.putExtra("isBoolean",isBoolean);
        setResult(2,intent);
        finish();
    }

    @Override
    protected void initListener() {

        mAdapter.setOnClickListener(new CollectAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(CollectActivity.this, WebActivity.class);
                intent.putExtra("niceDate", mAdapter.mBeans.get(position).getNiceDate());
                intent.putExtra("author", mAdapter.mBeans.get(position).getAuthor());
                intent.putExtra("superChapterName", mAdapter.mBeans.get(position).getCharSuperName());
                intent.putExtra("chapterName", mAdapter.mBeans.get(position).getCharName());
                intent.putExtra("id", mAdapter.mBeans.get(position).getId());
                intent.putExtra("url", mAdapter.mBeans.get(position).getLink());
                intent.putExtra("title", mAdapter.mBeans.get(position).getTitle());
                startActivityForResult(intent, 100);
            }
        });

        mAdapter.setIvOnClickListener(new CollectAdapter.IvOnClickListener() {
            @Override
            public void ivOnClick(int position) {
                DaoUtil.getBaseUtil().delete(mAdapter.mBeans.get(position));
                mAdapter.mBeans.remove(position);
                mAdapter.notifyDataSetChanged();
                ToastUtil.showShort("取消收藏");
                isBoolean = true;
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
        mPresenter.initData();
    }

    @Override
    public void onSuccess(List<Bean> bean) {
        mAdapter.setData(bean);
    }

}
