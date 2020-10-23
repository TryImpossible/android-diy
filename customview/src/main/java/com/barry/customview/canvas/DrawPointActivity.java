package com.barry.customview.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;

public class DrawPointActivity extends CanvasActivity {
    @Override
    void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(0xff8bc5ba); // 设置颜色
        paint.setStrokeWidth(50 * mDensity); // 设置线宽，如果不设置线宽，无法绘制点

        int w = canvas.getWidth();
        int h = canvas.getHeight();
        int x = w / 2;
        int deltaY = h / 3;
        int y = deltaY / 2;

        // 绘制Cap为BUTT的点
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(x, y, paint);

        // 绘制Cap为ROUND的点
        canvas.translate(0, deltaY);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(x, y, paint);

        // 绘制Cap为SQUARE的点
        canvas.translate(0, deltaY);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(x, y, paint);
    }
}
