package com.example.wanandroid.ui.wx.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.http.bean.OffcialTabBean;
import com.example.wanandroid.presenter.OffcialPresenter;
import com.example.wanandroid.ui.wx.adapter.OffcialVpAdapter;
import com.example.wanandroid.view.OffcialView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by 张嘉河 on 2019/4/28.
 */

public class OfficialFragment extends BaseFragment<OffcialView, OffcialPresenter> implements OffcialView {


    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_official;
    }

    @Override
    protected OffcialPresenter createPresenter() {
        return new OffcialPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mPresenter.initData();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onSuccess(OffcialTabBean bean) {
        List<OffcialTabBean.DataBean> data = bean.getData();
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            OffcialChildFragment fragment = new OffcialChildFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name",data.get(i).getName());
            bundle.putInt("id",data.get(i).getId());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        OffcialVpAdapter adapter = new OffcialVpAdapter(getChildFragmentManager(), data, fragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
