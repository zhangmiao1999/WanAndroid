package com.example.wanandroid.model;

import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.base.BaseObserver;
import com.example.wanandroid.http.bean.NavigationBean;
import com.example.wanandroid.http.bean.OffcialListBean;
import com.example.wanandroid.http.ApiService;
import com.example.wanandroid.http.bean.WxWeekBean;
import com.example.wanandroid.util.HttpUtils;
import com.example.wanandroid.util.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 张嘉河 on 2019/5/3.
 */

public class OffcialChildModel extends BaseModel {

    public interface CallBack{
        void onSuccess(OffcialListBean bean);
    }
    public void initData(int page, int id, final CallBack callBack) {
        HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl,ApiService.class)
                .getOffcialList(id,page)
                .compose(RxUtils.<OffcialListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<OffcialListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(OffcialListBean offcialListBean) {
                            callBack.onSuccess(offcialListBean);
                    }
                });
    }
}
