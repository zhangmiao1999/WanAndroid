package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.http.bean.HomeBannerBean;
import com.example.wanandroid.http.bean.HomeListBean;
import com.example.wanandroid.model.HomeModel;

import com.example.wanandroid.view.HomeView;

/**
 * Created by 张嘉河 on 2019/4/29.
 */

public class HomePresenter extends BasePresenter<HomeView> {

    private HomeModel mModel;

    @Override
    protected void initModel() {
        mModel = new HomeModel();
        mBaseModels.add(mModel);
    }
    public void initBanner() {
        mModel.initBanner(new HomeModel.CallBackBanner() {
            @Override
            public void onSuccess(HomeBannerBean homeBannerBean) {
                if (homeBannerBean!=null){
                    if (mView!=null){
                        mView.onSuccessBanner(homeBannerBean);
                    }
                }
            }
        });
    }

    public void initList(int page) {
        mModel.initList(page, new HomeModel.CallBackList() {
            @Override
            public void onSuccess(HomeListBean homeListBean) {
                if (homeListBean!=null&& homeListBean.getData().getDatas()!=null&& homeListBean.getData().getDatas().size()>0){
                    if (mView!=null){
                        mView.onSuccessArticle(homeListBean);
                    }
                }
            }
        });
    }


}
