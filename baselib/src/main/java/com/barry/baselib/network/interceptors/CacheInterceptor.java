package com.barry.baselib.network.interceptors;

import com.barry.baselib.core.BaseApplication;
import com.barry.baselib.utils.LogUtils;
import com.barry.baselib.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {

    private static final String TAG = CacheInterceptor.class.getSimpleName();

    // 接口缓存天数，14天
    private static final int CACHE_TIME = 14 * 24 * 60 * 60;
    /**
     * 获取缓存，
     * 添加了only-if-cached， 没有缓存时，会显示504错误，  不加就有缓存显示缓存，没有就网络请求
     */
    public final static String CACHE_CONTROL = "public,only-if-cached, max-stale=" + CACHE_TIME;


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isConnected(BaseApplication.getContext())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            LogUtils.e(TAG, "no network");
        }

        Response originalResponse = chain.proceed(request);
        if (!NetworkUtils.isConnected(BaseApplication.getContext())) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            if (cacheControl != null) {
                cacheControl = "public, max-age=0";
            }
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", CACHE_CONTROL)
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
