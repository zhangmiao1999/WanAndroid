package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.http.bean.ProjectTabBean;
import com.example.wanandroid.model.ProjectModel;
import com.example.wanandroid.view.ProjectView;

/**
 * Created by 张嘉河 on 2019/4/30.
 */

public class ProjectPresenter extends BasePresenter<ProjectView> {

    private ProjectModel mModel;

    public void initData() {
        mModel.initData(new ProjectModel.CallBack() {
            @Override
            public void onSuccess(ProjectTabBean bean) {
                if (bean!=null){
                    mView.onSuccess(bean);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        mModel = new ProjectModel();
        mBaseModels.add(mModel);
    }
}
