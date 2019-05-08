package com.example.wanandroid.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.http.bean.CommonListBean;
import com.example.wanandroid.presenter.CommonPresenter;
import com.example.wanandroid.ui.web.Web2Activity;
import com.example.wanandroid.util.UsedWebsitesUtil;
import com.example.wanandroid.view.CommonView;
import com.example.wanandroid.widget.FlowLayout;

import java.util.List;

import butterknife.BindView;

public class CommonActivity extends BaseActivity<CommonView, CommonPresenter> implements CommonView {

    private static final String TAG = "CommonActivity";
    @BindView(R.id.toolBarTitle)
    TextView mToolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.flowLayout)
    FlowLayout mFlowLayout;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_common;
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected void initView() {
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.initData();
    }

    @Override
    public void onSuccess(CommonListBean bean) {
        Log.d(TAG, "onSuccess: " + bean.getData().toString());
        final List<CommonListBean.DataBean> data = bean.getData();
        for (int i = 0; i < data.size(); i++) {
            //获取视图,视图可以自定义,可以添加自己想要的效果
            TextView lable = (TextView) LayoutInflater.from(this).inflate(R.layout.item_lable, null);
            //获取数据
            final String name = data.get(i).getName();
            final String link = data.get(i).getLink();
            Log.d(TAG, "name: " + name);
            //取得资源的引用。
            //随机设置颜色
            lable.setBackgroundColor(Color.parseColor(UsedWebsitesUtil.getRandomColor()));

            lable.setText(name);

            lable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CommonActivity.this, Web2Activity.class);
                    intent.putExtra("title",name);
                    intent.putExtra("url",link);
                    startActivity(intent);
                }
            });

            mFlowLayout.addView(lable);
        }

    }

}
