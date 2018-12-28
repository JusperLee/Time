package com.lj.Time.common;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.support.multidex.MultiDexApplication;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.lj.Time.db.DBManager;
import com.lj.Time.service.ProcessPlanService;
import com.lj.Time.util.CrashHandler;
import com.lj.Time.util.FileUtils;
import com.zhangke.zlog.ZLog;

/**
 * ApplicationInfo
 */

public class SZApplication extends MultiDexApplication {

    private static final String TAG = "ZLDApplication";
    private static SZApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        ZLog.Init(String.format("%s/log/", FileUtils.getDiskCacheDirPath(this)));

        DBManager.getInstance().init(this);

        if (debug()) {
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(this);
        } else {
            UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
            UMConfigure.setLogEnabled(debug());
            MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        }

        startService(new Intent(this, ProcessPlanService.class));
    }

    public static SZApplication getInstance() {
        return application;
    }

    public static boolean debug() {
        try {
            ApplicationInfo info = getInstance().getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }
}
