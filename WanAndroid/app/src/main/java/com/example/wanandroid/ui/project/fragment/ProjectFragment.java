package com.example.wanandroid.ui.project.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.http.bean.ProjectTabBean;
import com.example.wanandroid.presenter.ProjectPresenter;
import com.example.wanandroid.ui.project.adapter.ProjectVpAdapter;
import com.example.wanandroid.util.Logger;
import com.example.wanandroid.view.ProjectView;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 张嘉河 on 2019/4/28.
 */

public class ProjectFragment extends BaseFragment<ProjectView, ProjectPresenter> implements ProjectView {

    private static final String TAG = "ProjectFragment";
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    Unbinder unbinder;
    @BindView(R.id.project_tab_layout)
    SlidingTabLayout mProjectTabLayout;
    Unbinder unbinder1;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.initData();
    }

    @Override
    protected void initView() {

    }


    @Override
    public void onSuccess(ProjectTabBean bean) {
        Logger.logD("ProjectFragment", "onSuccess: " + bean.toString());
        List<ProjectTabBean.DataBean> data = bean.getData();
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ProjectChildFragment childFragment = new ProjectChildFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("cid", data.get(i).getId());
            childFragment.setArguments(bundle);
            fragments.add(childFragment);
        }
        ProjectVpAdapter adapter = new ProjectVpAdapter(getChildFragmentManager(), data, fragments);
        mViewPager.setAdapter(adapter);
        mProjectTabLayout.setViewPager(mViewPager);
    }

}
