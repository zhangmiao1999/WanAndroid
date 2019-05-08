package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.http.bean.KnowledgeBean;
import com.example.wanandroid.model.KnowledgeModel;

import com.example.wanandroid.view.KnowledgeView;

/**
 * Created by 张嘉河 on 2019/5/1.
 */

public class KnowledgePresenter extends BasePresenter<KnowledgeView> implements KnowledgeModel.CallBack  {

    private KnowledgeModel mModel;

    public void initData() {
        mModel.initData(this);
    }

    @Override
    public void onSuccess(KnowledgeBean bean) {
        if (bean!=null){
            mView.onSuccess(bean);
        }
    }

    @Override
    protected void initModel() {
        mModel = new KnowledgeModel();
        mBaseModels.add(mModel);
    }
}
