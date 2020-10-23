package com.barry.customview.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;

public class DrawLineActivity extends CanvasActivity {
    @Override
    void draw(Canvas canvas) {
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        float translateY = 30 * mDensity;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
//        paint.setStrokeWidth(2 * mDensity);

//        canvas.save();
        canvas.drawLine(0, 0, w, translateY, paint);
//        canvas.restore();

        canvas.translate(0, translateY * 3);
        canvas.drawLines(new float[]{0, 0, w / 2, w / 3, w / 2, w / 3, w, 0}, paint);

        canvas.translate(0, w / 2 + translateY);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStrokeWidth(5 * mDensity);
        canvas.drawLine(10 * mDensity,0, 150 * mDensity, 0, paint);

        canvas.translate(0, translateY);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(10 * mDensity,0, 150 * mDensity, 0, paint);

        canvas.translate(0, translateY);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(10 * mDensity,0, 150 * mDensity, 0, paint);
    }
}
