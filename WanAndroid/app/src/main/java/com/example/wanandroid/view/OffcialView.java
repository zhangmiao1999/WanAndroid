package com.example.wanandroid.view;

import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.http.bean.OffcialTabBean;

/**
 * Created by 张嘉河 on 2019/5/3.
 */

public interface OffcialView extends BaseView {
    void onSuccess(OffcialTabBean bean);
}
