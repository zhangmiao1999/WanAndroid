package com.example.wanandroid.model;

import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.base.BaseObserver;
import com.example.wanandroid.http.bean.OffcialTabBean;
import com.example.wanandroid.http.ApiService;
import com.example.wanandroid.util.HttpUtils;
import com.example.wanandroid.util.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 张嘉河 on 2019/5/3.
 */

public class OffcialModel extends BaseModel {

    public interface CallBack{
        void onSuccess(OffcialTabBean bean);
    }
    public void initData(final CallBack callBack) {
        HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl,ApiService.class)
                .getWx()
                .compose(RxUtils.<OffcialTabBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<OffcialTabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(OffcialTabBean offcialTabBean) {
                        callBack.onSuccess(offcialTabBean);
                    }
                });
    }
}
