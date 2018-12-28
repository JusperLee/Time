package com.lj.Time.util;

import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.zhangke.zlog.ZLog;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 异常捕获
 */

public class CrashHandler implements UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";

    private static CrashHandler sInstance = new CrashHandler();
    private Context mContext;
    //系统默认的异常处理（默认情况下，系统会终止当前的异常程序）
    private UncaughtExceptionHandler mDefaultCrashHandler;

    public static CrashHandler getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();

        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        dumpException(ex);
        ex.printStackTrace();
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(thread, ex);
        } else {
            Process.killProcess(Process.myPid());
        }
    }

    private void dumpException(Throwable ex) {
        try {
            StringBuilder sbMessage = new StringBuilder();
            sbMessage.append(ex.getMessage());
            sbMessage.append("\n");
            StackTraceElement[] stackTrace = ex.getStackTrace();
            for (StackTraceElement s : stackTrace) {
                sbMessage.append(s.toString());
                sbMessage.append("\n");
            }

            ZLog.openSaveToFile();
            ZLog.crash(TAG, sbMessage.toString());
        } catch (Exception e) {
            Log.e(TAG, "dump crash i failed");
            e.printStackTrace();
        }
    }
}
