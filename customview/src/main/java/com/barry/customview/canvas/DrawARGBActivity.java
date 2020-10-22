package com.barry.customview.canvas;

import android.graphics.Canvas;

public class DrawARGBActivity extends CanvasActivity {

    @Override
    void draw(Canvas canvas) {
        canvas.drawARGB(255, 139, 197, 186);
    }
}