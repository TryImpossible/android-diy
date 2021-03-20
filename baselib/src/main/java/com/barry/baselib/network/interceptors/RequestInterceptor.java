package com.barry.baselib.network.interceptors;

import okhttp3.logging.HttpLoggingInterceptor;

public class LoggingInterceptor extends HttpLoggingInterceptor {
    @Override
    public HttpLoggingInterceptor setLevel(Level level) {
        return super.setLevel(level);
    }
}
