package com.barry.performance;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.github.moduth.blockcanary.BlockCanary;

public class MainApplication extends Application {

    private static Context sContext;

    public static Context getAppContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
//        initStrictMode();

        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }

    private void initStrictMode() {
        if (BuildConfig.DEBUG) {
            //线程
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectCustomSlowCalls() //API等级11，使用StrictMode.noteSlowCode
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()// 或者直接使用 .detectAll() 手机全部信息
                    .penaltyDialog()//弹窗违规提示框
                    .penaltyLog() //在Logcat 中打印违规异常信息，还可以选择弹框提示或者直接奔溃等
                    .penaltyFlashScreen()
                    .build());
            //虚拟机
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectActivityLeaks()//activity 泄漏
                    .detectLeakedSqlLiteObjects()//泄漏SQlite对象
//                    .setClassInstanceLimit(StrictModeTest.class, 1)
                    .detectLeakedClosableObjects() //API等级11，未关闭的closeable对象泄漏
                    .penaltyDropBox()
                    .penaltyLog()
                    .build());
        }
    }
}
