package com.barry.baselib.mvp;

import android.content.Context;

import com.barry.baselib.core.BaseView;

public abstract class BasePresenter<V extends BaseView> {

    protected Context mContext;

    private V mView;

    public V getmView() {
        return mView;
    }

    public void attachView(V v) {
        mView = v;
    }

    public void detachView() {
        mView = null;
    }

    public BasePresenter(Context mContext) {
        this.mContext = mContext;
    }
}
