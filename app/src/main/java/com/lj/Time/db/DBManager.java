package com.lj.Time.db;

import android.content.Context;

import com.lj.Time.common.SZApplication;

/**
 * 数据库管理类
 */

public class DBManager {

    private static final String DB_NAME = "data.db";
    private com.lj.Time.db.DaoSession daoSession;

    private static DBManager dbManager;

    public static DBManager getInstance() {
        if (dbManager == null) {
            synchronized (DBManager.class) {
                if (dbManager == null) {
                    dbManager = new DBManager();
                }
            }
        }
        return dbManager;
    }

    public void init(Context context) {
        com.lj.Time.db.DaoMaster.DevOpenHelper helper = new com.lj.Time.db.DaoMaster.DevOpenHelper(context, DB_NAME);

        com.lj.Time.db.DaoMaster daoMaster = new com.lj.Time.db.DaoMaster(helper.getWritableDatabase());

        daoSession = daoMaster.newSession();
    }

    public void clear(){
        daoSession.clear();
    }

    public com.lj.Time.db.ApplicationInfoDao getApplicationInfoDao() {
        return daoSession.getApplicationInfoDao();
    }

    public RationPlanDao getRationPlanDao() {
        return daoSession.getRationPlanDao();
    }

    public RationRecordDao getRationRecordDao(){
        return daoSession.getRationRecordDao();
    }

    public com.lj.Time.db.ClockPlanDao getClockPlanDao(){
        return daoSession.getClockPlanDao();
    }

    public com.lj.Time.db.ClockRecordDao getClockRecordDao(){
        return daoSession.getClockRecordDao();
    }

    public TodoDao getTodoDao(){
        return daoSession.getTodoDao();
    }
}
