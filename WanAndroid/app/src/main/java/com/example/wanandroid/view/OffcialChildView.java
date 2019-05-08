package com.example.wanandroid.view;

import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.http.bean.OffcialListBean;
import com.example.wanandroid.http.bean.WxWeekBean;

/**
 * Created by 张嘉河 on 2019/5/3.
 */

public interface OffcialChildView extends BaseView {
    void onSuccess(OffcialListBean bean);

}
