package com.barry.performance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MemoryLeakActivity1 extends AppCompatActivity {

    private String mTest = "TEST_STR";
    private Handler mHandler = new Handler();
    private MyRunnable mMyRunnable;

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            Log.d("test", mTest);
            testMethod();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak1);

        setTitle("测试内存临时泄漏");

        mMyRunnable = new MyRunnable();
        mHandler.postDelayed(mMyRunnable, 10000);

////         销毁当前 Activity
//        finish();
    }

    private void testMethod() {

    }
}