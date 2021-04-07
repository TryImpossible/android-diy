package com.barry.baselib.activity;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * @description Activity管理类
 * @author barry
 * create at 4/6/21 11:48 AM
 * ================================================
 */
public class ActivityManager {

    public List<Activity> activities = new ArrayList<>();

    private static ActivityManager sInstance = new ActivityManager();

    private WeakReference<Activity> sCurrentActivityWeakRef;

    public static ActivityManager getInstance() {
        return sInstance;
    }

    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }

    public void setCurrentActivity(Activity activity) {
        sCurrentActivityWeakRef = new WeakReference<Activity>(activity);
    }

    public void addActivity(Activity activity) {
        if (null != activities) {
            activities.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (null != activities && activities.contains(activity)) {
            activities.remove(activity);
        }
    }

    public void clearActivity() {
        if (null != activities && activities.size() > 0) {
            for (int i = 0; i < activities.size() - 1; i++) {
                Activity ac = activities.get(i);
                ac.finish();
            }
            activities.clear();
        }
    }
}
