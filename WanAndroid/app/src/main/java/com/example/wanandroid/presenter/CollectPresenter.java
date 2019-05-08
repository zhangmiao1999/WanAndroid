package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.http.bean.Bean;
import com.example.wanandroid.model.CollectModel;
import com.example.wanandroid.view.CollectView;

import java.util.List;

/**
 * Created by 张嘉河 on 2019/5/6.
 */

public class CollectPresenter extends BasePresenter<CollectView> {

    private CollectModel mModel;

    @Override
    protected void initModel() {
        mModel = new CollectModel();
        mBaseModels.add(mModel);
    }

    public void initData() {
        mModel.initData(new CollectModel.CallBack() {
            @Override
            public void onSuccess(List<Bean> bean) {
                if (bean!=null){
                    if (mView!=null){
                        mView.onSuccess(bean);
                    }
                }
            }
        });
    }
}
