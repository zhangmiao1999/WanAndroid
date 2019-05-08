package com.example.wanandroid.http;


import com.example.wanandroid.http.bean.CommonListBean;
import com.example.wanandroid.http.bean.HomeBannerBean;
import com.example.wanandroid.http.bean.HomeListBean;
import com.example.wanandroid.http.bean.HotWeelBean;
import com.example.wanandroid.http.bean.KnowledgeBean;
import com.example.wanandroid.http.bean.KnowledgeChildBean;
import com.example.wanandroid.http.bean.NavigationBean;
import com.example.wanandroid.http.bean.OffcialListBean;
import com.example.wanandroid.http.bean.OffcialTabBean;
import com.example.wanandroid.http.bean.ProjectListBean;
import com.example.wanandroid.http.bean.ProjectTabBean;
import com.example.wanandroid.http.bean.WxWeekBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 张嘉河 on 2019/4/29.
 */

public interface ApiService {
    String sBaseUrl = "https://www.wanandroid.com/";

    //https://www.wanandroid.com/article/list/0/json?cid=60
    @GET("banner/json")
    Observable<HomeBannerBean> getBanner();

    @GET("article/list/{page}/json")
    Observable<HomeListBean> getArticle(@Path("page") int page);

    @GET("project/tree/json")
    Observable<ProjectTabBean> getTab();

    @GET("tree/json")
    Observable<KnowledgeBean> getKnowledge();

    @GET("wxarticle/chapters/json")
    Observable<OffcialTabBean> getWx();

    @GET("article/list/{page}/json")
    Observable<KnowledgeChildBean> getKnowledgeChild(@Path("page") int page, @Query("cid") int cid);

    @GET("https://wanandroid.com/wxarticle/list/{id}/{page}/json")
    Observable<OffcialListBean> getOffcialList(@Path("id") int id, @Path("page") int page);

    @GET("project/list/1/json")
    Observable<ProjectListBean> getProjectList(@Query("cid") int id);

    @GET("friend/json")
    Observable<CommonListBean> getCommon();

    @GET("hotkey/json")
    Observable<HotWeelBean> getHotWeek();

    @GET("navi/json")
    Observable<NavigationBean> getNavigation();

    // TODO: 2019/5/8  微信查询，
    @GET("wxarticle/list/405/1/json")
    Observable<WxWeekBean> getWeek(@Query("k") String k);

}
