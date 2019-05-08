package com.example.wanandroid.model;

import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.base.BaseObserver;
import com.example.wanandroid.http.ApiService;
import com.example.wanandroid.http.bean.CommonListBean;
import com.example.wanandroid.http.bean.ProjectListBean;
import com.example.wanandroid.util.HttpUtils;
import com.example.wanandroid.util.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 张嘉河 on 2019/5/5.
 */

public class CommonModel extends BaseModel {
    public interface CallBack{
        void onSuccess(CommonListBean bean);
    }

    public void initData(final CallBack callBack) {
        HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl,ApiService.class)
                .getCommon()
                .compose(RxUtils.<CommonListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CommonListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(CommonListBean commonListBean) {
                        callBack.onSuccess(commonListBean);
                    }
                });
    }
}
