package com.barry.baselib.core;

public interface BaseView {
    void showProgress(String title, String prompt);

    void showProgress();

    void dismissProgress();

    boolean isAlive();
}
