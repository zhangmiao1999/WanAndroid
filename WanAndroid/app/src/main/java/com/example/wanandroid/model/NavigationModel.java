package com.example.wanandroid.model;

import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.base.BaseObserver;
import com.example.wanandroid.http.ApiService;
import com.example.wanandroid.http.bean.NavigationBean;
import com.example.wanandroid.util.HttpUtils;
import com.example.wanandroid.util.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 张嘉河 on 2019/5/4.
 */

public class NavigationModel extends BaseModel {

    public interface CallBack {
        void onSuccess(NavigationBean bean);
    }
    public void initData(final CallBack callBack) {
        HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl,ApiService.class)
                .getNavigation()
                .compose(RxUtils.<NavigationBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<NavigationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(NavigationBean navigationBean) {
                        callBack.onSuccess(navigationBean);
                    }
                });
    }
}
