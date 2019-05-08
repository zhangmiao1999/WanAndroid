package com.example.wanandroid.ui.navigation.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.example.wanandroid.R;

import java.util.ArrayList;

import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * Created by 张嘉河 on 2019/5/5.
 */

public class TabAdapter implements q.rorbin.verticaltablayout.adapter.TabAdapter {
    private ArrayList<String> mTitles;
    private Context mContext;

    public TabAdapter(ArrayList<String> titles, Context context) {

        mTitles = titles;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public ITabView.TabBadge getBadge(int position) {
        return null;
    }

    @Override
    public ITabView.TabIcon getIcon(int position) {
        return null;
    }

    @Override
    public ITabView.TabTitle getTitle(int position) {
        return new TabView.TabTitle.Builder()
                .setContent(mTitles.get(position))
                .setTextColor(ContextCompat.getColor(mContext, R.color.color4AC3A5),
                        ContextCompat.getColor(mContext, R.color.car_backgroud))
                .build();
    }

    @Override
    public int getBackground(int position) {
        return 0;
    }
}
