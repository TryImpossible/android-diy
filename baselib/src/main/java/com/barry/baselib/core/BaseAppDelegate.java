package com.barry.baselib.core;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import androidx.multidex.MultiDex;

import com.barry.baselib.network.cookies.CookieStore;
import com.barry.baselib.utils.DensityUtils;
import com.barry.baselib.utils.SpanUtils;

public class BaseAppDelegate {

    private Application mApplication;

    static {

    }

    public BaseAppDelegate(Application mApplication) {
        this.mApplication = mApplication;
    }

    public void onCreate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            // 7.0严格模式
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }

        // 解决(# fields: 72756 > 65536)
        MultiDex.install(mApplication);
        // 初始化屏幕密度
        DensityUtils.setDensity(mApplication);
        // 初始化SpanUtils
        SpanUtils.init(mApplication);
        // Cookies持久化Preference参数
        CookieStore.init(mApplication);
    }
}
