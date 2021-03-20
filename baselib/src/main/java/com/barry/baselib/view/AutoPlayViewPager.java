/*
 *
 * *******************************************************************
 *   @项目名称: BHex Android
 *   @文件名称: AutoPlayViewPager.java
 *   @Date: 11/29/18 3:21 PM
 *   @Author: chenjun
 *   @Copyright（C）: 2018 BlueHelix Inc.   All rights reserved.
 *   注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的.
 *  *******************************************************************
 *
 */

package com.barry.baselib.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.barry.baselib.utils.LogUtils;

/**
 * 自动进行循环播放的ViewPager
 * ================================================
 */
public class AutoPlayViewPager extends ViewPager {

    private static final int DEFAULT_AUTO_PLAY_TIME = 5000;

    private boolean isRunning;

    private int auto_play_time = DEFAULT_AUTO_PLAY_TIME;

    public AutoPlayViewPager(Context context) {
        super(context);
    }

    public AutoPlayViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("parent", super.onSaveInstanceState());
        bundle.putInt("auto_play_time", auto_play_time);
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;

            auto_play_time = bundle.getInt("auto_play_time");

            state = bundle.getParcelable("parent");
        }

        super.onRestoreInstanceState(state);
    }

    /**
     * 保证wrap_content的效果
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;

        if (getLayoutParams() != null && getLayoutParams().height > 0) {
            height = getLayoutParams().height;
        } else {


            //下面遍历所有child的高度
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);

                int childHeight = 0;
                if (child.getLayoutParams() != null && child.getLayoutParams().height > 0) {
                    childHeight = child.getLayoutParams().height;
                } else {
                    child.measure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(child.getLayoutParams().height, View.MeasureSpec.AT_MOST));
                    childHeight = child.getMeasuredHeight();
                }

                if (childHeight > height) //采用最大的view的高度。
                {
                    height = childHeight;
                }
            }
        }

        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //防止内存泄漏
        stop();
    }

    public void setAdapter(AutoPlayPagerAdapter adapter) {
        this.setAdapter((PagerAdapter) adapter);
    }

    @Override
    @Deprecated
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);

        setCurrentItem(((AutoPlayPagerAdapter) adapter).getDefaultPos());

        start();
    }

    @Override
    public AutoPlayPagerAdapter getAdapter() {
        return (AutoPlayPagerAdapter) super.getAdapter();
    }

    /**
     * 开始自动播放
     */
    public void start() {
        if (isRunning)
            return;

        removeCallbacks(play);
        isRunning = true;
        postDelayed(play, auto_play_time);
    }

    public void stop() {
        isRunning = false;
        removeCallbacks(play);
    }

    private Runnable play = new Runnable() {
        @Override
        public void run() {
            PagerAdapter adapter = getAdapter();
            if (adapter == null || !isRunning)
                return;

            int nextItem = getCurrentItem() + 1;
            if (nextItem < adapter.getCount())
                setCurrentItem(nextItem, true);
            else
                setCurrentItem(0, false);
            postDelayed(play, auto_play_time);
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //触摸时，不做自动播放处理
        try {

            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_HOVER_MOVE:
                    stop();
                    break;
                case MotionEvent.ACTION_UP:
//                case MotionEvent.ACTION_CANCEL:
                    start();
                    break;
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }

        return super.dispatchTouchEvent(ev);
    }
}
