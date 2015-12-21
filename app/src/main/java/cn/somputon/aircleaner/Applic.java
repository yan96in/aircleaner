package cn.somputon.aircleaner;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;

import com.tencent.bugly.crashreport.CrashReport;

import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 002 on 2015/12/12.
 */
public class Applic extends android.app.Application {
    public static boolean isLogin;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "900014970", false);
        CrashReport.testJavaCrash();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志
    }



    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this
                .getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i
                    .next());
            try {
                if (info.pid == pID) {
                    CharSequence c = pm.getApplicationLabel(pm
                            .getApplicationInfo(info.processName,
                                    PackageManager.GET_META_DATA));
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
            }
        }
        return processName;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        try {
            deleteCacheDirFile(getHJYCacheDir(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.gc();
    }

    public static Context getInstance() {
        return context;
    }

    // 运用list来保存们每一个activity是关键
    private List<Activity> mList = new LinkedList<Activity>();
    private static Applic instance;

    // 构造方法
    // 实例化一次
    public synchronized static Applic getInstance2() {
        if (null == instance) {
            instance = new Applic();
        }
        return instance;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    // 关闭每一个list内的activity
    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public static String getHJYCacheDir() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
            return Environment.getExternalStorageDirectory().toString()
                    + "/Health/Cache";
        else
            return "/System/com.juns.Walk/Walk/Cache";
    }

    public static String getHJYDownLoadDir() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
            return Environment.getExternalStorageDirectory().toString()
                    + "/Walk/Download";
        else {
            return "/System/com.Juns.Walk/Walk/Download";
        }
    }

    public static void deleteCacheDirFile(String filePath,
                                          boolean deleteThisPath) throws IOException {
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            if (file.isDirectory()) {// 处理目录
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteCacheDirFile(files[i].getAbsolutePath(), true);
                }
            }
            if (deleteThisPath) {
                if (!file.isDirectory()) {// 如果是文件，删除
                    file.delete();
                } else {// 目录
                    if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                        file.delete();
                    }
                }
            }
        }
    }
}
