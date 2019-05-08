package com.example.wanandroid.view;

import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.http.bean.ProjectListBean;


/**
 * Created by 张嘉河 on 2019/4/30.
 */

public interface ProjectChildView extends BaseView {
    void onSuccess(ProjectListBean bean);
}
