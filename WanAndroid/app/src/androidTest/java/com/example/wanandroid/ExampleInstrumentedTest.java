package com.example.wanandroid;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.wanandroid.base.BaseObserver;
import com.example.wanandroid.http.bean.HomeBannerBean;
import com.example.wanandroid.http.ApiService;
import com.example.wanandroid.util.HttpUtils;
import com.example.wanandroid.util.RxUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.disposables.Disposable;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = "ExampleInstrumentedTest";

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.wanandroid", appContext.getPackageName());
    }

    public void run(){
        HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl,ApiService.class)
                .getBanner()
                .compose(RxUtils.<HomeBannerBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeBannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(HomeBannerBean homeBannerBean) {
                        Log.d(TAG, "onNext: "+homeBannerBean.getData().toString());
                    }
                });
    }
}
