package com.example.wanandroid.view;

import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.http.bean.HotWeelBean;

/**
 * Created by 张嘉河 on 2019/5/5.
 */

public interface WeekView extends BaseView {
    void onSuccess(HotWeelBean bean);
}
