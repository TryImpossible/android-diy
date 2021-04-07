package com.barry.baselib.contract;

import android.content.Context;

import com.barry.baselib.core.BaseView;
import com.barry.baselib.mvp.BasePresenter;

public interface NullContract {
    interface View extends BaseView {
    }

    abstract class Presenter extends BasePresenter<View> {
        public Presenter(Context mContext) {
            super(mContext);
        }
    }
}
