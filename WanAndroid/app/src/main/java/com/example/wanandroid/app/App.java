package com.example.wanandroid.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by 张嘉河 on 2019/5/3.
 */

public class App extends Application {
    private static App sInstance;
    //默认不是夜间模式
    public static int mMode = AppCompatDelegate.MODE_NIGHT_NO;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App getInstance() {
        return sInstance;
    }
}
