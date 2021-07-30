package com.barry.performance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.lang.ref.WeakReference;

public class FixMemoryLeakActivity1 extends AppCompatActivity {
    private String mTest = "TEST_STR";
    private Handler mHandler = new Handler();
    private MyRunnable mMyRunnable;

    static class MyRunnable implements Runnable {
        private WeakReference<FixMemoryLeakActivity1> mWeakReference;

        public MyRunnable(FixMemoryLeakActivity1 activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void run() {
            FixMemoryLeakActivity1 activity = mWeakReference.get();
            if (activity != null) {
                Log.d("test", activity.getTest());
                activity.mFunction();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_memory_leak1);

        setTitle("解决内存泄漏");
        mMyRunnable = new MyRunnable(this);
        mHandler.postDelayed(mMyRunnable, 10000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMyRunnable != null) {
            mHandler.removeCallbacks(mMyRunnable);
        }
    }

    public String getTest() {
        return this.mTest;
    }

    public void mFunction() {
    }
}