package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.http.bean.CommonListBean;
import com.example.wanandroid.model.CommonModel;
import com.example.wanandroid.view.CommonView;

/**
 * Created by 张嘉河 on 2019/5/5.
 */

public class CommonPresenter extends BasePresenter<CommonView> {

    private CommonModel mModel;

    @Override
    protected void initModel() {
        mModel = new CommonModel();
        mBaseModels.add(mModel);
    }

    public void initData() {
        mModel.initData(new CommonModel.CallBack() {
            @Override
            public void onSuccess(CommonListBean bean) {
                if (bean!=null&& bean.getData()!=null&& bean.getData().size()>0){
                    if (mView!=null){
                        mView.onSuccess(bean);
                    }
                }
            }
        });
    }
}
