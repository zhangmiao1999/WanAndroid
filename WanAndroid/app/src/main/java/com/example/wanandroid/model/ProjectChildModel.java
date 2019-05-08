package com.example.wanandroid.model;



import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.base.BaseObserver;
import com.example.wanandroid.http.bean.ProjectListBean;
import com.example.wanandroid.http.ApiService;
import com.example.wanandroid.util.HttpUtils;
import com.example.wanandroid.util.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by 张嘉河 on 2019/4/30.
 */

public class ProjectChildModel extends BaseModel{

    public interface CallBack{
        void onSuccess(ProjectListBean bean);
    }
    public void initData(int id, final CallBack callBack) {
        HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl,ApiService.class)
                .getProjectList(id)
                .compose(RxUtils.<ProjectListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ProjectListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ProjectListBean projectListBean) {
                        callBack.onSuccess(projectListBean);
                    }
                });
    }
}
