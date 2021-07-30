package com.barry.performance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MemoryReleaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_release);

        setTitle("测试内存释放");
    }
}