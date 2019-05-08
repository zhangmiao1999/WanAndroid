package com.example.wanandroid.view;

import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.http.bean.Bean;

import java.util.List;

/**
 * Created by 张嘉河 on 2019/5/6.
 */

public interface CollectView extends BaseView {
    void onSuccess(List<Bean> bean);
}
