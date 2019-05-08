package com.example.wanandroid.view;

import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.http.bean.KnowledgeBean;


/**
 * Created by 张嘉河 on 2019/5/1.
 */

public interface KnowledgeView extends BaseView {
    void onSuccess(KnowledgeBean bean);
}
