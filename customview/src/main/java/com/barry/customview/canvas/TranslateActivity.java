package com.barry.customview.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class TranslateActivity extends CanvasActivity {

    @Override
    void draw(Canvas canvas) {
        Paint paint = new Paint();

        // 画一个400大小的正方形
        canvas.drawRect(new RectF(0, 0, 400, 400), paint);

        // 向右平移500，向下平移20
        canvas.translate(500, 20);

        // 再画一个400大小的正方形
        canvas.drawRect(new RectF(0, 0, 400, 400), paint);

        // 向右平移200，向下平移200
        canvas.translate(200, 200);

        // 再画一个400大小的正方形
        canvas.drawRect(new RectF(0, 0, 400, 400), paint);

    }
}
