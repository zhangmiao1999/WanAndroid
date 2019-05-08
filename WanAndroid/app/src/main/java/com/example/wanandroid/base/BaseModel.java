package com.example.wanandroid.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by 张嘉河 on 2019/5/3.
 */

public class BaseModel {
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public void onDestroy() {
        mCompositeDisposable.clear();
    }
}
