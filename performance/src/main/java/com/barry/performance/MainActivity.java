package com.barry.performance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.debug.hv.ViewServer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("性能优化");

        ViewServer.get(this).addWindow(this);

        Log.i("MainActivity", "testing log lint");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);

        //给主线程造成卡顿，在子线程中获取锁，并让主线中等待20s，在让它获取锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MainActivity.this) {
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //申请这把锁
        synchronized (MainActivity.this) {
            Log.i("TAG", "");
        }
    }

    public void test1(View view) {
        startActivity(new Intent(this, MemoryReleaseActivity.class));
    }

    public void test2(View view) {
        startActivity(new Intent(this, MemoryLeakActivity1.class));
    }

    public void test3(View view) {
        startActivity(new Intent(this, MemoryLeakActivity2.class));
    }

    public void test4(View view) {
        startActivity(new Intent(this, FixMemoryLeakActivity1.class));
    }

    public void test5(View view) {
        startActivity(new Intent(this, TraceViewActivity1.class));
    }

    public void test6(View view) {
        startActivity(new Intent(this, BlockActivity1.class));
    }
}