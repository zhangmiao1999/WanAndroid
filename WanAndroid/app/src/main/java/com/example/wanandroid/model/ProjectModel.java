package com.example.wanandroid.model;


import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.base.BaseObserver;
import com.example.wanandroid.http.bean.ProjectTabBean;
import com.example.wanandroid.http.ApiService;
import com.example.wanandroid.util.HttpUtils;
import com.example.wanandroid.util.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 张嘉河 on 2019/4/30.
 */

public class ProjectModel extends BaseModel{
    private static final String TAG = "ProjectModel";

    public interface CallBack{
        void onSuccess(ProjectTabBean bean);
    }

    public void initData(final CallBack callBack) {
        HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl,ApiService.class)
                .getTab()
                .compose(RxUtils.<ProjectTabBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ProjectTabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ProjectTabBean projectTabBean) {
                            callBack.onSuccess(projectTabBean);
                    }
                });
    }
}
