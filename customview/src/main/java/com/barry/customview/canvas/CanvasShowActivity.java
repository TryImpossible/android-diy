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

        findViewById(R.id.btn_translate).setOnClickListener(view -> startActivity(new Intent(this, TranslateActivity.class)));
        findViewById(R.id.btn_draw_axis).setOnClickListener(view -> startActivity(new Intent(this, DrawAxisActivity.class)));
        findViewById(R.id.btn_draw_argb).setOnClickListener(view -> startActivity(new Intent(this, DrawARGBActivity.class)));
        findViewById(R.id.btn_draw_text).setOnClickListener(view -> startActivity(new Intent(this, DrawTextActivity.class)));
        findViewById(R.id.btn_draw_point).setOnClickListener(view -> startActivity(new Intent(this, DrawPointActivity.class)));
        findViewById(R.id.btn_draw_line).setOnClickListener(view -> startActivity(new Intent(this, DrawLineActivity.class)));
        findViewById(R.id.btn_draw_rect).setOnClickListener(view -> startActivity(new Intent(this, DrawRectActivity.class)));
        findViewById(R.id.btn_draw_circle).setOnClickListener(view -> startActivity(new Intent(this, DrawCircleActivity.class)));
        findViewById(R.id.btn_draw_oval).setOnClickListener(view -> startActivity(new Intent(this, DrawOvalActivity.class)));
        findViewById(R.id.btn_draw_arc).setOnClickListener(view -> startActivity(new Intent(this, DrawArcActivity.class)));
        findViewById(R.id.btn_draw_path).setOnClickListener(view -> startActivity(new Intent(this, DrawPathActivity.class)));
        findViewById(R.id.btn_draw_bitmap).setOnClickListener(view -> startActivity(new Intent(this, DrawBitmapActivity.class)));
        findViewById(R.id.btn_draw_love).setOnClickListener(view -> startActivity(new Intent(this, DrawLoveActivity.class)));
    }
}