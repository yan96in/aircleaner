package cn.somputon.aircleaner;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

import org.xutils.x;

/**
 * Created by 002 on 2015/12/12.
 */
public class Aplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "900014970", false);
        CrashReport.testJavaCrash();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志
    }
}
