package com.example.wanandroid.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.SimpleActivity;
import com.example.wanandroid.http.bean.HotWeelBean;
import com.example.wanandroid.presenter.WeekPresenter;
import com.example.wanandroid.ui.web.Web2Activity;
import com.example.wanandroid.util.UsedWebsitesUtil;
import com.example.wanandroid.view.WeekView;
import com.example.wanandroid.widget.FlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeekActivity extends BaseActivity<WeekView,WeekPresenter> implements WeekView {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.searchView)
    EditText mSearchView;
    @BindView(R.id.week_but)
    Button mWeekBut;
    @BindView(R.id.flowLayout)
    FlowLayout mFlowLayout;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_week;
    }

    @Override
    protected WeekPresenter createPresenter() {
        return new WeekPresenter();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mPresenter.initData();
    }

    @Override
    public void onSuccess(HotWeelBean bean) {
        List<HotWeelBean.DataBean> data = bean.getData();
        for (int i = 0; i < data.size(); i++) {
            //获取视图,视图可以自定义,可以添加自己想要的效果
            TextView lable = (TextView) LayoutInflater.from(this).inflate(R.layout.item_lable, null);
            //获取数据
            final String name = data.get(i).getName();
            final String link = data.get(i).getLink();
            //取得资源的引用。
            //随机设置颜色
            lable.setBackgroundColor(Color.parseColor(UsedWebsitesUtil.getRandomColor()));

            lable.setText(name);

            lable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(WeekActivity.this, Web2Activity.class);
                    intent.putExtra("title",name);
                    intent.putExtra("url",link);
                    startActivity(intent);
                }
            });

            mFlowLayout.addView(lable);
        }
    }
}
