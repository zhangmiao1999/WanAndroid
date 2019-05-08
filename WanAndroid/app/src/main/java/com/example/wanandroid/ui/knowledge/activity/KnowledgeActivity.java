package com.example.wanandroid.ui.knowledge.activity;

import android.app.ActivityThread;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.knowledge.adapter.KnowledgeVpAdapter;
import com.example.wanandroid.base.SimpleActivity;
import com.example.wanandroid.http.bean.KnowledgeBean;
import com.example.wanandroid.ui.knowledge.fragment.KnowledgeChildFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class KnowledgeActivity extends SimpleActivity {
    ActivityThread mActivityThread;

    private static final String TAG = "KnowledgeActivity";
    @BindView(R.id.toolBarTitle)
    TextView mToolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private ArrayList<KnowledgeBean.DataBean> mBean;
    private KnowledgeVpAdapter mAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_knowledge;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mBean = (ArrayList<KnowledgeBean.DataBean>) intent.getSerializableExtra("bean");
        int position = intent.getIntExtra("position", 0);
        setToolBar(mToolBar);
        setToolBarTitle(mToolBarTitle, mBean.get(position).getName());
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < mBean.get(position).getChildren().size(); i++) {
            KnowledgeChildFragment childFragment = new KnowledgeChildFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id",mBean.get(position).getChildren().get(i).getId());
            childFragment.setArguments(bundle);
            fragments.add(childFragment);
        }

        mAdapter = new KnowledgeVpAdapter(getSupportFragmentManager(), mBean.get(position).getChildren(),fragments);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initListener() {
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
