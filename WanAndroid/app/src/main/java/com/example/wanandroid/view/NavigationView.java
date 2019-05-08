package com.example.wanandroid.view;

import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.http.bean.NavigationBean;

/**
 * Created by 张嘉河 on 2019/5/4.
 */

public interface NavigationView extends BaseView {
    void onSuccess(NavigationBean bean);
}
