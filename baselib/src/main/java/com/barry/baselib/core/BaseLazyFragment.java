package com.barry.baselib.core;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.barry.baselib.utils.LogUtils;

import java.util.List;

/**
 * https://juejin.im/post/5adcb0e36fb9a07aa7673fbc
 * BaseLazyFragment 单fragment懒加载
 * <p>
 * * 生命周期执行的方法 如下：
 * 第一次生成页面-->可见
 * setUserVisibleHint: ----->false
 * setUserVisibleHint: ----->true
 * onCreateView: -----> onCreateView
 * onStart: -----> onStart
 * onFragmentFirst: 首次可见
 * onFragmentFirst: -----> 子fragment进行初始化操作
 * onResume: -----> onResume
 * <p>
 * 可见-->第一次隐藏：
 * onPause: -----> onPause
 * onFragmentInVisible: 不可见
 * <p>
 * 未销毁且不可见-->重新可见：
 * onStart: -----> onStart
 * onFragmentVisble: 可见
 * onFragmentVisble: -----> 子fragment每次可见时的操作
 * onResume: -----> onResume
 * <p>
 * 可见-->销毁：
 * onPause: -----> onPause
 * onFragmentInVisible: 不可见
 * onDestroyView: -----> onDestroyView
 * <p>
 * 我们可以更具以上生命周期来操作不同的业务逻辑，
 * 请务必运行此module demo，观看打印日志来自定义逻辑。
 */
public abstract class BaseLazyFragment extends Fragment implements BaseView {

    public Context mContext;
    protected View rootView;

    private boolean isFirstVisible = true;/*当前Fragment是否首次可见，默认是首次可见**/

    private boolean isViewCreated = false;/*当前Fragment的View是否已经创建**/

    private boolean currentVisibleState = false;/*当前Fragment的可见状态，一种当前可见，一种当前不可见**/

    public BaseLazyFragment() {
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

        if (isViewCreated) {
            //Fragment可见且状态不是可见(从一个Fragment切换到另外一个Fragment,后一个设置状态为可见)
            if (isVisibleToUser && !currentVisibleState) {
                dispatchFragment(true);
            } else if (!isVisibleToUser && currentVisibleState) {
                //Fragment不可见且状态是可见(从一个Fragment切换到另外一个Fragment,前一个更改状态为不可见)
                dispatchFragment(false);
            }
        }
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
        isViewCreated = true;//在onCreateView执行完毕，将isViewCreated改为true;
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

        //isHidden()是Fragment是否处于隐藏状态和isVisible()有区别
        //getUserVisibleHint(),Fragement是否可见
        if (!isHidden() && getUserVisibleHint()) {//如果Fragment没有隐藏且可见
            //执行分发的方法,三种结果对应自Fragment的三个回调，对应的操作，Fragment首次加载，可见，不可见
            dispatchFragment(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i(getClass().getSimpleName() + "------enter------");

        if (!isFirstVisible) {
            //表示点击home键又返回操作,设置可见状态为ture
            if (!isHidden() && !getUserVisibleHint() && currentVisibleState) {
                dispatchFragment(true);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.i(getClass().getSimpleName() + "------enter------");

        //表示点击home键,原来可见的Fragment要走该方法，更改Fragment的状态为不可见
        if (!isHidden() && getUserVisibleHint()) {
            dispatchFragment(false);
        }
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

        //当 View 被销毁的时候我们需要重新设置 isViewCreated mIsFirstVisible 的状态
        isViewCreated = false;
        isFirstVisible = true;

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

    //由子类指定具体类型
    public abstract int getLayoutId();

    /**
     * @param visible Fragment当前是否可见，然后调用相关方法
     */
    public void dispatchFragment(boolean visible) {
        currentVisibleState = visible;
        if (visible) {//Fragment可见
            if (isFirstVisible) {//可见又是第一次
                isFirstVisible = false;//改变首次可见的状态
                onFragmentFirst();
            } else {//可见但不是第一次
                onFragmentVisible();
            }
        } else {//不可见
            onFragmentInVisible();
        }
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

    //Fragment首次可见的方法
    public void onFragmentFirst() {
        LogUtils.e(getClass().getSimpleName(), "首次可见");
    }

    //Fragment可见的方法
    public void onFragmentVisible() {//子Fragment调用次方法，执行可见操作
        LogUtils.e(getClass().getSimpleName(), "可见");
    }

    //Fragment不可见的方法
    public void onFragmentInVisible() {
        LogUtils.e(getClass().getSimpleName(), "不可见");
    }
}

