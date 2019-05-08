package com.example.wanandroid.ui.knowledge.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.wanandroid.http.bean.KnowledgeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张嘉河 on 2019/5/4.
 */

public class KnowledgeVpAdapter extends FragmentStatePagerAdapter {
    private final List<KnowledgeBean.DataBean.ChildrenBean> mBean;
    private final ArrayList<Fragment> mFragments;

    public KnowledgeVpAdapter(FragmentManager fm, List<KnowledgeBean.DataBean.ChildrenBean> bean, ArrayList<Fragment> fragments) {
        super(fm);
        mBean = bean;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mBean.get(position).getName();
    }
}
