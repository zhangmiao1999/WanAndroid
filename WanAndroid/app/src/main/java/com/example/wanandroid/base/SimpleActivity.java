package com.example.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by 张嘉河 on 2019/5/3.
 */

public abstract class SimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        initView();
        initListener();
    }

    protected void setToolBar(Toolbar toolBar) {
        toolBar.setTitle("");
        setSupportActionBar(toolBar);
    }

    protected void setToolBarTitle(TextView mToolbar, String title) {
        mToolbar.setText(title);
    }

    protected void initListener() {

    }

    protected void initView() {

    }

    protected abstract int setLayoutId();

}
