package com.barry.baselib.mvp;

import com.barry.baselib.core.BaseView;

public abstract class BasePresenter<V extends BaseView> {
    
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
}
