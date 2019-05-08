package com.example.wanandroid.model;


import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.base.BaseObserver;
import com.example.wanandroid.http.bean.HomeBannerBean;
import com.example.wanandroid.http.bean.HomeListBean;
import com.example.wanandroid.http.ApiService;
import com.example.wanandroid.util.HttpUtils;
import com.example.wanandroid.util.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 张嘉河 on 2019/4/29.
 */

public class HomeModel extends BaseModel{

    private static final String TAG = "HomeModel";

    public interface CallBackBanner {
        void onSuccess(HomeBannerBean homeBannerBean);
    }

    public interface CallBackList {
        void onSuccess(HomeListBean homeListBean);
    }

    public void initBanner(final CallBackBanner callback) {
        HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl,ApiService.class)
                .getBanner()
                .compose(RxUtils.<HomeBannerBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeBannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HomeBannerBean homeBannerBean) {
                        callback.onSuccess(homeBannerBean);
                    }
                });
    }

    public void initList(int page, final CallBackList callback) {
        HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl,ApiService.class)
                .getArticle(page)
                .compose(RxUtils.<HomeListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HomeListBean homeListBean) {
                            callback.onSuccess(homeListBean);
                    }
                });
    }
}
