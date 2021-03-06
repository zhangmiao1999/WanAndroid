package com.example.codelibrary;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by 张嘉河 on 2019/4/29.
 */

public abstract class ObServerCallBack<T> implements Observer<T> {
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void onSubscribe(Disposable d) {
        mCompositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        String error = "";
        if (e instanceof ApiException){
            ApiException apiException = (ApiException) e;
            error = apiException.getMessage();
        }else if (e instanceof HttpException){
            HttpException httpException = (HttpException) e;
            error = httpException.message();
        }
        onError(error);
        if (mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
    }

    protected abstract void onError(String error);

    @Override
    public void onComplete() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
