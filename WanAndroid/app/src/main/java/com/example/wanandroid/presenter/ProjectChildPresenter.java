package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;

import com.example.wanandroid.http.bean.ProjectListBean;
import com.example.wanandroid.model.ProjectChildModel;
import com.example.wanandroid.view.ProjectChildView;

/**
 * Created by 张嘉河 on 2019/4/30.
 */

public class ProjectChildPresenter extends BasePresenter<ProjectChildView> {

    private ProjectChildModel mModel;

    public void initData(int id) {
        mModel.initData(id, new ProjectChildModel.CallBack() {
            @Override
            public void onSuccess(ProjectListBean bean) {
                if (bean!=null){
                    if (mView!=null){
                        mView.onSuccess(bean);
                    }
                }
            }
        });
    }

    @Override
    protected void initModel() {
        mModel = new ProjectChildModel();
        mBaseModels.add(mModel);
    }
}
