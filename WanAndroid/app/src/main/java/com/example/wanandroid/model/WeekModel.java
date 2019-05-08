package com.example.wanandroid.model;

import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.base.BaseObserver;
import com.example.wanandroid.http.ApiService;
import com.example.wanandroid.http.bean.HotWeelBean;
import com.example.wanandroid.util.HttpUtils;
import com.example.wanandroid.util.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 张嘉河 on 2019/5/5.
 */

public class WeekModel extends BaseModel {

    public  interface CallBack{
        void onSuccess(HotWeelBean bean);
    }
    public void initData(final CallBack callBack) {
        HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl,ApiService.class)
                .getHotWeek()
                .compose(RxUtils.<HotWeelBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HotWeelBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HotWeelBean hotWeelBean) {
                        callBack.onSuccess(hotWeelBean);
                    }
                });
    }
}
