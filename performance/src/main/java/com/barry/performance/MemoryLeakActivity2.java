package com.barry.performance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MemoryLeakActivity2 extends AppCompatActivity {
    private String mTest = "TEST_STR";
    private Handler mHandler = new Handler();

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            Log.d("test", mTest);
            mHandler.postDelayed(this, 10000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak2);

        setTitle("测试内存一直泄漏");

        mHandler.postDelayed(new MyRunnable(), 10000);
    }
}