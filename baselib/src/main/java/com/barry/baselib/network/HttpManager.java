package com.barry.baselib.network;

import com.barry.baselib.core.BaseApplication;
import com.barry.baselib.network.cookies.CookieManager;
import com.barry.baselib.network.interceptors.CacheInterceptor;
import com.barry.baselib.network.interceptors.LoggingInterceptor;
import com.barry.baselib.network.interceptors.RequestInterceptor;
import com.barry.baselib.network.interceptors.ResponseInterceptor;

import java.io.File;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpManager {
    // http缓存，15Mib
    private static final int DISK_CACHE_SIZE = 30 * 1024 * 1024;
    // 统一超时时间，单位秒
    private static final int DEFAULT_TIMEOUT = 60;

    private volatile static HttpManager instance;

    private final OkHttpClient okHttpClient;

    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    private HttpManager() {
        // 缓存
        Cache cache = new Cache(new File(BaseApplication.getContext().getCacheDir(), "http-cache"), DISK_CACHE_SIZE);
        // host验证
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        okHttpClient = new OkHttpClient.Builder()
                // 超时
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                // 日志
                .addInterceptor(LoggingInterceptor.getInstance())
                // 公共参数
                .addInterceptor(new RequestInterceptor())
                .addInterceptor(new ResponseInterceptor())
                // 缓存
                .addNetworkInterceptor(new CacheInterceptor())
                .cache(cache)
                // cookie
                .cookieJar(new CookieManager())
                .hostnameVerifier(hostnameVerifier)
                // ssl
                .sslSocketFactory(getSSLSocketFactory())
                .build();
    }

    /**
     * 不验证证书
     *
     * @return
     * @throws Exception
     */
    private SSLSocketFactory getSSLSocketFactory() {
        //创建一个不验证证书链的证书信任管理器。
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};
        final SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            return sslContext
                    .getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <S> S createService(Class<S> serviceClass, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

}
