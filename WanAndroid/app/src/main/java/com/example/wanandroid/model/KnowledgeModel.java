package com.example.wanandroid.model;


import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.base.BaseObserver;
import com.example.wanandroid.http.bean.KnowledgeBean;
import com.example.wanandroid.http.ApiService;
import com.example.wanandroid.util.HttpUtils;
import com.example.wanandroid.util.RxUtils;

import io.reactivex.disposables.Disposable;


/**
 * Created by 张嘉河 on 2019/5/1.
 */

public class KnowledgeModel extends BaseModel{
    private static final String TAG = "KnowledgeModel";

    public interface CallBack{
        void onSuccess(KnowledgeBean bean);
    }

    public void initData(final CallBack callBack) {
        HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl,ApiService.class)
                .getKnowledge()
                .compose(RxUtils.<KnowledgeBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<KnowledgeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(KnowledgeBean knowledgeBean) {
                        callBack.onSuccess(knowledgeBean);
                    }
                });

    }
}
