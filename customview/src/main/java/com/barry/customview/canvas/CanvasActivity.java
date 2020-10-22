package com.barry.customview.canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.barry.customview.R;

public abstract class CanvasActivity extends AppCompatActivity {

    protected float mDensity = 1;

    private View mCanvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        mDensity = dm.density;

        mCanvasView = new View(this) {
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                CanvasActivity.this.draw(canvas);
            }
        };
        ((LinearLayout)findViewById(R.id.container)).addView(mCanvasView);
    }

    abstract void draw(Canvas canvas);
}