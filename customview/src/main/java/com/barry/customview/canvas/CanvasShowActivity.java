package com.barry.customview.canvas;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.barry.customview.R;

public class CanvasShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_show);

        findViewById(R.id.btn_draw_axis).setOnClickListener(view -> startActivity(new Intent(this, DrawAxisActivity.class)));
    }
}