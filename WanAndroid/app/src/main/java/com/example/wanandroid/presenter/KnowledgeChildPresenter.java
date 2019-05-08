package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.http.bean.KnowledgeChildBean;
import com.example.wanandroid.model.KnowledgeChildModel;
import com.example.wanandroid.view.KnowledgeChildView;

/**
 * Created by 张嘉河 on 2019/5/4.
 */

public class KnowledgeChildPresenter extends BasePresenter<KnowledgeChildView> {

    private KnowledgeChildModel mModel;

    @Override
    protected void initModel() {
        mModel = new KnowledgeChildModel();
        mBaseModels.add(mModel);
    }

    public void initData(int page, int id) {
        mModel.initData(page, id, new KnowledgeChildModel.CallBack() {
            @Override
            public void onSuccess(KnowledgeChildBean bean) {
                if (bean!=null&& bean.getData()!=null){
                    if (mView!=null){
                        mView.onSuccess(bean);
                    }
                }
            }
        });
    }
}
