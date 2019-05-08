package com.example.wanandroid.ui.project.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.example.wanandroid.http.bean.ProjectTabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张嘉河 on 2019/4/30.
 */

public class ProjectVpAdapter extends FragmentStatePagerAdapter {
    private final List<ProjectTabBean.DataBean> mData;
    private final ArrayList<Fragment> mFragments;

    public ProjectVpAdapter(FragmentManager fm, List<ProjectTabBean.DataBean> data, ArrayList<Fragment> fragments) {
        super(fm);
        mData = data;
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
        return mData.get(position).getName();
    }
}
