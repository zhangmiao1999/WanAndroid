package com.example.wanandroid.view;

import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.http.bean.ProjectTabBean;


/**
 * Created by 张嘉河 on 2019/4/30.
 */

public interface ProjectView extends BaseView {
    void onSuccess(ProjectTabBean bean);
}
