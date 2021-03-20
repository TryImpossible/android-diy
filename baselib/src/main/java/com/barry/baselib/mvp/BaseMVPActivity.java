package com.barry.baselib.mvp;

import android.os.Bundle;

import com.barry.baselib.core.BaseCoreActivity;
import com.barry.baselib.core.BaseView;

public abstract class BaseMVPActivity<V extends BaseView, P extends BasePresenter<V>> extends BaseCoreActivity {

    //引用V层和P层
    private P presenter;

    public V view;

    public P getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (view == null) {
            createView();
        }
        if (presenter != null && view != null) {
            presenter.attachView(this.view);
        }
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    //由子类指定具体类型
    public abstract P createPresenter();

    public abstract V createView();

    public abstract void init();
}
