package com.example.wanandroid.view;

import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.http.bean.HomeBannerBean;
import com.example.wanandroid.http.bean.HomeListBean;


/**
 * Created by 张嘉河 on 2019/4/29.
 */

public interface HomeView extends BaseView {
    void onSuccessBanner(HomeBannerBean homeBannerBean);

    void onSuccessArticle(HomeListBean homeListBean);
}
