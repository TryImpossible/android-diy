package com.barry.baselib.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 修改density 屏幕适配方案
 */
public class DensityUtils {

    private static String TAG = DensityUtils.class.getSimpleName();

    /**
     * 设计图的宽度， 6sp尺寸
     */
    private final static float DESIGN_WIDTH = 375f;
    /**
     * 设计图的高度, 6sp尺寸
     */
    private final static float DESIGN_HEIGHT = 667f;

    private static float appDensity;
    private static float appScaledDensity;
    private static DisplayMetrics appDisplayMetrics;
    //private static int barHeight;

    public static void setDensity(@NonNull final Application application) {
        //获取application的DisplayMetrics
        appDisplayMetrics = application.getResources().getDisplayMetrics();
        //获取状态栏高度
        //barHeight = getStatusBarHeight(application);

        if (appDensity == 0) {
            //初始化的时候赋值
            appDensity = appDisplayMetrics.density;
            appScaledDensity = appDisplayMetrics.scaledDensity;

            //添加字体变化的监听
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    //字体改变后,将appScaledDensity重新赋值
                    if (newConfig != null && newConfig.fontScale > 0) {
                        appScaledDensity =
                                application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {
                }
            });
        }
    }


    /**
     * 更改适配方向
     *
     * @param activity
     */
    public static void setOrientationWidth(Activity activity, boolean isLand) {
        setAppOrientation(activity, "width", isLand);
    }

    public static void setOrientationHeight(Activity activity, boolean isLand) {
        setAppOrientation(activity, "height", isLand);
    }

    /**
     * targetDensity
     * targetScaledDensity
     * targetDensityDpi
     * 这三个参数是统一修改过后的值
     * <p>
     * orientation:方向值,传入width或height
     */
    private static void setAppOrientation(@Nullable Activity activity, String orientation, boolean isLand) {

        float targetDensity;

        int shortSize = appDisplayMetrics.widthPixels;
        int longSize;
        //偶尔，横竖屏切换瞬间， height width值没切换过来， 这里以长短边来计算， 不管是width 还是 height
        if (shortSize < appDisplayMetrics.heightPixels) {
            longSize = appDisplayMetrics.heightPixels;
        } else {
            longSize = shortSize;
            shortSize = appDisplayMetrics.heightPixels;
        }

        Log.d(TAG, "set app orienttation  "
                + orientation
                + longSize
                + "/"
                + shortSize
                + " "
                + appDisplayMetrics.heightPixels);
        if (orientation.equals("height")) {
            //以高度适配， 横屏显示， 就用长边， 竖屏
            //float tager = isLand ? appDisplayMetrics.widthPixels : appDisplayMetrics.heightPixels;
            targetDensity = longSize / DESIGN_HEIGHT;
        } else {
            //以宽度适配， 都是用短边
            //float tager = isLand ? appDisplayMetrics.heightPixels : appDisplayMetrics.widthPixels;
            targetDensity = shortSize / DESIGN_WIDTH;
        }

        float targetScaledDensity = targetDensity * (appScaledDensity / appDensity);
        int targetDensityDpi = (int) (160 * targetDensity);

        /**
         *
         * 最后在这里将修改过后的值赋给系统参数
         *
         * 只修改Activity的density值
         */

        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

    //private static int getStatusBarHeight(Context context) {
    //    int result = 0;
    //    int resourceId =
    //            context.getResources().getIdentifier("status_bar_height", "dimen", "android");
    //    if (resourceId > 0) {
    //        result = context.getResources().getDimensionPixelSize(resourceId);
    //    }
    //    return result;
    //}
}