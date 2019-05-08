package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.http.bean.HotWeelBean;
import com.example.wanandroid.model.WeekModel;
import com.example.wanandroid.view.WeekView;

/**
 * Created by 张嘉河 on 2019/5/5.
 */

public class WeekPresenter extends BasePresenter<WeekView> {

    private WeekModel mModel;

    @Override
    protected void initModel() {
        mModel = new WeekModel();
        mBaseModels.add(mModel);
    }

    public void initData() {
        mModel.initData(new WeekModel.CallBack() {
            @Override
            public void onSuccess(HotWeelBean bean) {
                if (bean!=null&& bean.getData()!=null&& bean.getData().size()>0){
                    if (mView!=null){
                        mView.onSuccess(bean);
                    }
                }
            }
        });
    }
}
