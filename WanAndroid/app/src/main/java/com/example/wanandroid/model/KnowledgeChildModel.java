package com.example.wanandroid.model;

import android.util.Log;

import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.base.BaseObserver;
import com.example.wanandroid.http.bean.KnowledgeChildBean;
import com.example.wanandroid.http.ApiService;
import com.example.wanandroid.util.HttpUtils;
import com.example.wanandroid.util.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 张嘉河 on 2019/5/4.
 */

public class KnowledgeChildModel extends BaseModel {

    private static final String TAG = "KnowledgeChildModel";

    public interface CallBack{
        void onSuccess(KnowledgeChildBean bean);
    }

    public void initData(int page, int id, final CallBack callBack){
        HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl,ApiService.class)
                .getKnowledgeChild(page,id)
                .compose(RxUtils.<KnowledgeChildBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<KnowledgeChildBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(KnowledgeChildBean knowledgeChildBean) {
                        callBack.onSuccess(knowledgeChildBean);
                        Log.d(TAG, "onNext: "+knowledgeChildBean.toString());
                    }
                });
    }
}
