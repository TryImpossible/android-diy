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

package io.bhex.baselib.mvp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Method;
import java.util.List;

import io.bhex.baselib.utils.DebugLog;


public abstract class BaseCoreFragment extends Fragment {

    protected View rootView;

    public BaseCoreFragment() {
        super();
        setArguments(new Bundle());
    }

    /**
     * 这个不会由Fragment自身的生命周期发起 而是由 {@link android.support.v4.app.FragmentPagerAdapter}
     * 和 {@link android.support.v4.app.FragmentStatePagerAdapter} 来调用，所以一般情况下，只有在ViewPager
     * 中才会有
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        DebugLog.i(getClass().getSimpleName() + "------enter------  userVisible:" + isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DebugLog.i(getClass().getSimpleName() + "------enter------");

        if (rootView == null) {
            setRootView(createView(inflater, container, savedInstanceState));
            //createView(getLayoutId(),container,false)
            executeOnceAfterCreateView();
        }

        if (rootView.getParent() != null)
            ((ViewGroup) rootView.getParent()).removeView(rootView);

        return rootView;
    }

    /**
     * 在Fragment show hide 的时候被调用，但是第一次不会调用，可以查看{@link android.support.v4.app.FragmentManager}
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

        DebugLog.i(getClass().getSimpleName() + "------enter------ hidden:" + hidden);
    }

    /**
     * 只执行一次
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void executeOnceAfterCreateView();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onStart() {
        super.onStart();
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onResume() {
        super.onResume();

//        List<Fragment> frgs = getChildFragmentManager().getFragments();
//        if (frgs != null)
//            for (Fragment item : frgs) {
//                if (item != null) {
//                    item.onResume();
//                }
//            }

        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onPause() {
        super.onPause();

//        List<Fragment> frgs = getChildFragmentManager().getFragments();
//        if (frgs != null)
//            for (Fragment item : frgs) {
//                if (item != null) {
//                    item.onPause();
//                }
//            }

//        deliverCall2Child("onPause");
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    protected void deliverCall2Child(String methodName, Object... params) {
        List<Fragment> frgs = getChildFragmentManager().getFragments();
        if (frgs == null)
            return;

        for (Fragment item : frgs) {
            if (item == null)
                return;

            try {
                Class clazz = Class.forName(item.getClass().getName());
                Method method = clazz.getMethod(methodName);
                method.invoke(item, params);
            } catch (Exception e) {
                DebugLog.e(e);
            }

            item.onPause();
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    protected void setRootView(View rootView) {
        this.rootView = rootView;
    }

    public View getRootView() {
        return rootView;
    }

    /**
     * 状态栏颜色
     */
    protected final int setStatusBarColor() {
        return ((BaseCoreActivity)getActivity()).setStatusBarColor();
    }
    /**
     * 切换状态栏
     *
     * @param color
     */
    protected final void switchStatusBar(int color) {
        ((BaseCoreActivity)getActivity()).switchStatusBar(color);
    }
}
