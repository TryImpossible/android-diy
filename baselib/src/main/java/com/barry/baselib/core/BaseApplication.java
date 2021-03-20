package com.barry.baselib.core;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * ================================================
 * @description 使用BaseApplication，必须在组件中实现自己的Application，并且继承BaseApplication；
 *              组件模块中实现的Application必须在debug包中的AndroidManifest.xml中注册，否则无法使用；
 * @author barry
 * create at 2021/3/16 15:21
 * ================================================
 */
public abstract class BaseApplication extends Application {

    private static Context sContext;
    private static Handler sHandler;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sHandler = new Handler(getMainLooper());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sContext = base;
    }

    protected void init() {
        BaseAppDelegate appDelegate = new BaseAppDelegate(this);
        appDelegate.onCreate();
    }
}
