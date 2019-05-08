package com.example.wanandroid.model;

import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.http.bean.Bean;
import com.example.wanandroid.util.DaoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张嘉河 on 2019/5/6.
 */

public class CollectModel extends BaseModel {
    public interface CallBack{
        void onSuccess(List<Bean> bean);
    }
    public void initData(CallBack callBack) {
        List query = DaoUtil.getBaseUtil().query();
        callBack.onSuccess(query);
    }
}
