package com.example.wanandroid.util;

import com.example.wanandroid.app.App;
import com.example.wanandroid.http.bean.Bean;
import com.example.wanandroid.http.dao.BeanDao;
import com.example.wanandroid.http.dao.DaoMaster;
import com.example.wanandroid.http.dao.DaoSession;


import java.util.List;

/**
 * Created by 张嘉河 on 2019/5/6.
 */

public class DaoUtil {
    private static DaoUtil sBaseUtil;
        private final BeanDao mBeanDao;

        public DaoUtil() {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getInstance(), "info" + ".dp");

            DaoMaster daoMaster = new DaoMaster(devOpenHelper.getReadableDatabase());

            DaoSession daoSession = daoMaster.newSession();

            mBeanDao = daoSession.getBeanDao();
        }

        public static DaoUtil getBaseUtil() {
            if (sBaseUtil == null){
                synchronized (DaoUtil.class){
                    if (sBaseUtil == null){
                        sBaseUtil = new DaoUtil();
                    }
                }
            }
            return sBaseUtil;
        }

        public boolean has(Bean bean) {
            List<Bean> list = mBeanDao.queryBuilder().where(BeanDao.Properties.Title.eq(bean.getTitle())).list();
            if (list.size() > 0 && list != null) {
                return true;
            }
            return false;
        }

        public long insert(Bean bean) {
            if(!has(bean)){
               return mBeanDao.insertOrReplace(bean);
            }
            return -1;
        }

        public boolean delete(Bean bean) {
            if (has(bean)) {
                mBeanDao.delete(bean);
                return true;
            }
            return false;
        }

        public List query() {
            return mBeanDao.queryBuilder().list();
        }
}
