package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.http.bean.NavigationBean;
import com.example.wanandroid.model.NavigationModel;
import com.example.wanandroid.view.NavigationView;

/**
 * Created by 张嘉河 on 2019/5/4.
 */

public class NavigationPresenter extends BasePresenter<NavigationView> {

    private NavigationModel mModel;

    @Override
    protected void initModel() {
        mModel = new NavigationModel();
        mBaseModels.add(mModel);
    }

    public void initData() {
        mModel.initData(new NavigationModel.CallBack() {
            @Override
            public void onSuccess(NavigationBean bean) {
                if (bean!=null&& bean.getData()!=null){
                    if (mView!=null){
                        mView.onSuccess(bean);
                    }
                }
            }
        });
    }
}
