package com.example.wanandroid.ui.wx.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.wanandroid.http.bean.OffcialTabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张嘉河 on 2019/5/3.
 */

public class OffcialVpAdapter extends FragmentStatePagerAdapter {
    private final List<OffcialTabBean.DataBean> mData;
    private final ArrayList<Fragment> mFragments;

    public OffcialVpAdapter(FragmentManager fm, List<OffcialTabBean.DataBean> data, ArrayList<Fragment> fragments) {
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
