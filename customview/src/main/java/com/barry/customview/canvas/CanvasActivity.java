package com.barry.customview.canvas;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.barry.customview.R;

public abstract class CanvasActivity extends AppCompatActivity {

    protected float mDensity = 1;

    private View mCanvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        mDensity = getResources().getDisplayMetrics().density;

        mCanvasView = new View(this) {
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                CanvasActivity.this.draw(canvas);
            }
        };
        ((LinearLayout) findViewById(R.id.container)).addView(mCanvasView);
    }

    abstract void draw(Canvas canvas);
}