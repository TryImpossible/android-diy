package com.barry.baselib.activity;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.barry.baselib.R;
import com.barry.baselib.contract.NullContract;
import com.barry.baselib.fragment.WebFragment;
import com.barry.baselib.mvp.BaseMVPActivity;
import com.barry.baselib.presenter.NullPresenter;
import com.barry.baselib.view.HeaderView;

public class WebActivity extends BaseMVPActivity<NullContract.View, NullContract.Presenter> implements NullContract.View, WebFragment.WebFragmentListener {

    private HeaderView mHeaderView;
    private WebFragment mWebFragment;
    private boolean mHideMore = false;//隐藏右方更多操作
    private boolean mHasNav = true; //导航栏的显示状态
    protected String mWebViewUrl;

    @Override
    public NullContract.Presenter createPresenter() {
        return new NullPresenter(this);
    }

    @Override
    public NullContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        mWebViewUrl = getIntent().getStringExtra("url");
        if (TextUtils.isEmpty(mWebViewUrl)) {
            finish();
            return;
        }

        try {
            if (getIntent().hasExtra("hideMore")) {
                mHideMore = Boolean.parseBoolean(getIntent().getStringExtra("hideMore"));
            }
        } catch (Exception e) {
            mHideMore = false;
        }

        try {
            if (getIntent().hasExtra("hasNav")) {
                mHasNav = Boolean.parseBoolean(getIntent().getStringExtra("hasNav"));
            }
        } catch (Exception e) {
            mHasNav = true;
        }

        mHeaderView = findViewById(R.id.headerView);
        mHeaderView.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mWebFragment = new WebFragment(mWebViewUrl, false);
        mWebFragment.setListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mWebFragment).commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mWebFragment != null && mWebFragment.mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void setHeaderTitle(String title) {
        if (mHasNav) {
            mHeaderView.setTitleText(title);
        }
    }

    @Override
    public void setWebViewUrl(String url) {
        this.mWebViewUrl = url;
    }

    @Override
    public void setHeaderMore(boolean hideMore) {
        if (mHasNav) {
            mHeaderView.setMenuVisible(!hideMore);
        }
    }

    @Override
    public void closeWebView() {
        finish();
    }
}
