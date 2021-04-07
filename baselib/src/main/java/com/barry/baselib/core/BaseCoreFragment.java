/*
 *
 * *******************************************************************
 *   @项目名称: BHex Android
 *   @文件名称: BaseCoreFragment.java
 *   @Date: 11/29/18 3:21 PM
 *   @Author: chenjun
 *   @Copyright（C）: 2018 BlueHelix Inc.   All rights reserved.
 *   注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的.
 *  *******************************************************************
 *
 */

package com.barry.baselib.core;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.barry.baselib.utils.LogUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * ================================================
 * @description 最基础的Fragment类，所有原生Fragment都应继承它
 * @author barry
 * create at 2021/3/16 15:22
 * ================================================
 */
public abstract class BaseCoreFragment extends Fragment implements BaseView {

    public Context mContext;
    protected View rootView;

    public BaseCoreFragment() {
        super();
        setArguments(new Bundle());
    }

    /**
     * 这个不会由Fragment自身的生命周期发起 而是由 {@link androidx.fragment.app.FragmentPagerAdapter}
     * 和 {@link androidx.fragment.app.FragmentPagerAdapter} 来调用，所以一般情况下，只有在ViewPager
     * 中才会有
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.i(getClass().getSimpleName() + "------enter------  userVisible:" + isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(getClass().getSimpleName() + "------enter------");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.i(getClass().getSimpleName() + "------enter------");
        rootView = inflater.inflate(getLayoutId(), container, false);
        mContext = getActivity();
        return rootView;
    }

    /**
     * 在Fragment show hide 的时候被调用，但是第一次不会调用，可以查看{@link androidx.fragment.app.FragmentManager}
     * 源码，了解调用时机
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        List<Fragment> frgs = getChildFragmentManager().getFragments();
        if (frgs != null)
            for (Fragment item : frgs) {
                if (item != null && item.isAdded()) {
                    //两个有一个为隐藏 则认为是隐藏模式的
                    item.onHiddenChanged(hidden || item.isHidden());
                }
            }

        LogUtils.i(getClass().getSimpleName() + "------enter------ hidden:" + hidden);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public boolean isAlive() {
        return this.isAdded() && ((BaseCoreActivity) getActivity()).isAlive();
    }

    @Override
    public void showProgress(String title, String prompt) {
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void dismissProgress() {
    }

    //由子类指定具体类型
    public abstract int getLayoutId();
}
