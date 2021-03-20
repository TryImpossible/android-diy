package com.barry.baselib.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.barry.baselib.core.BaseLazyFragment;
import com.barry.baselib.core.BaseView;

public abstract class BaseMVPFragment<V extends BaseView, P extends BasePresenter<V>> extends BaseLazyFragment {
    //引用V层和P层
    private P presenter;
    private V view;

    public P getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
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
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    //由子类指定具体类型
    public abstract P createPresenter();

    public abstract V createView();

    public abstract void init();
}
