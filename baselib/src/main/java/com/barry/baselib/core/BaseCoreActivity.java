package com.barry.baselib.core;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.barry.baselib.utils.DensityUtils;
import com.barry.baselib.utils.KeyboardUtils;
import com.barry.baselib.utils.LogUtils;

import java.util.List;

/**
 * ================================================
 * @description 最基础的Activity类，所有原生Activity都应继承它
 * @author barry
 * create at 2021/3/16 15:23
 * ================================================
 */
public abstract class BaseCoreActivity extends AppCompatActivity implements BaseView {

    protected boolean isAlive = true;

    @Override
    public Resources getResources() {
//        return super.getResources();
        //TODO 待验证 默认系统设置 防止用户个性化修改系统字体大小，导致UI适配问题
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {
            Configuration config = new Configuration();
            config.setToDefaults();
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return res;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(getClass().getSimpleName() + "---onCreate---enter------");

        this.getResources();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        DensityUtils.setOrientationWidth(this, false);

        setContentView(getLayoutId());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i(getClass().getSimpleName() + "---onRestart---enter------");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.i(getClass().getSimpleName() + "---onRestoreInstanceState---enter------");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(getClass().getSimpleName() + "---onStart---enter------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i(getClass().getSimpleName() + "---onResume---enter------");

    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i(getClass().getSimpleName() + "----onPause--enter------");

        View v = getCurrentFocus();
        if (v instanceof EditText) {
            v.clearFocus();
            KeyboardUtils.hideKeyboard(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i(getClass().getSimpleName() + "---onStop---enter------");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtils.i(getClass().getSimpleName() + "---onConfigurationChanged---enter------");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.i(getClass().getSimpleName() + "---onSaveInstanceState---enter------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i(getClass().getSimpleName() + "---onSaveInstanceState---enter------");

        isAlive = false;
    }

    @Override
    public boolean isAlive() {
        return isAlive && !isFinishing();
    }

    @Override
    public void showProgress(String title, String prompt) {}

    @Override
    public void showProgress() {}

    @Override
    public void dismissProgress() {}

    //由子类指定具体类型
    public abstract int getLayoutId();
}