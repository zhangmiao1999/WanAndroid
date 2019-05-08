package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.http.bean.OffcialListBean;
import com.example.wanandroid.http.bean.WxWeekBean;
import com.example.wanandroid.model.OffcialChildModel;
import com.example.wanandroid.view.OffcialChildView;

/**
 * Created by 张嘉河 on 2019/5/3.
 */

public class OffcialChildPresenter extends BasePresenter<OffcialChildView> {

    private OffcialChildModel mModel;

    @Override
    protected void initModel() {
        mModel = new OffcialChildModel();
        mBaseModels.add(mModel);
    }

    public void initData(int page, int id) {
        mModel.initData(page, id, new OffcialChildModel.CallBack() {
            @Override
            public void onSuccess(OffcialListBean bean) {
                if (bean!=null&& bean.getData()!=null){
                    if (mView!=null){
                        mView.onSuccess(bean);
                    }
                }
            }
        });
    }

}
