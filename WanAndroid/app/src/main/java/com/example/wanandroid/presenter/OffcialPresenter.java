package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.http.bean.OffcialTabBean;
import com.example.wanandroid.model.OffcialModel;
import com.example.wanandroid.view.OffcialView;

/**
 * Created by 张嘉河 on 2019/5/3.
 */

public class OffcialPresenter extends BasePresenter<OffcialView> {

    private OffcialModel mModel;

    @Override
    protected void initModel() {
        mModel = new OffcialModel();
        mBaseModels.add(mModel);
    }

    public void initData() {
        mModel.initData(new OffcialModel.CallBack() {
            @Override
            public void onSuccess(OffcialTabBean bean) {
                if (bean!=null&& bean.getData()!=null&& bean.getData().size()>0){
                    if (mView!=null){
                        mView.onSuccess(bean);
                    }
                }
            }
        });
    }
}
