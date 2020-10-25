package com.barry.motionlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_simple_motion).setOnClickListener((view) -> startActivity(new Intent(this, SimpleMotionActivity.class)));
        findViewById(R.id.btn_android_motion1).setOnClickListener((view) -> startActivity(new Intent(this, AndroidIconMotion01Activity.class)));
        findViewById(R.id.btn_android_motion2).setOnClickListener((view) -> startActivity(new Intent(this, AndroidIconMotion02Activity.class)));
        findViewById(R.id.btn_collapsing_toolbar).setOnClickListener((view) -> startActivity(new Intent(this, CollapsingToolbarMotionActivity.class)));
    }
}