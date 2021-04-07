/*
 *
 * *******************************************************************
 *   @项目名称: BHex Android
 *   @文件名称: PixelUtils.java
 *   @Date: 11/29/18 3:21 PM
 *   @Author: chenjun
 *   @Copyright（C）: 2018 BlueHelix Inc.   All rights reserved.
 *   注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的.
 *  *******************************************************************
 *
 */

package com.barry.baselib.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.barry.baselib.core.BaseApplication;

/**
 * ================================================
 * @description 尺寸工具类
 * @author barry
 * create at 4/6/21 11:34 AM
 * ================================================
 */
public class PixelUtils {

    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获得屏幕高度
     *
     * @return
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) BaseApplication.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     *
     * @return
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) BaseApplication.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int dp2px(float value) {
        final float scale = BaseApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    public static int px2dp(float value) {
        final float scale = BaseApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (value / scale + 0.5f);
    }
}
