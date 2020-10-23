package com.barry.customview.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;

public class DrawCircleActivity extends CanvasActivity {
    @Override
    void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(0xff8bc5ba); // 设置颜色
        paint.setStyle(Paint.Style.FILL); // 默认为填充模式

        int w = canvas.getWidth();
        int h = canvas.getHeight();
        int halfW = w / 2;
        int count = 3;
        int D = h / (count + 1);
        int R = D / 2;

        canvas.save();
        canvas.drawLine(0, D, w, D, paint);
        canvas.restore();

        canvas.save();
        canvas.drawLine(0, 2 * D, w, 2 * D, paint);
        canvas.restore();

        canvas.save();
        canvas.drawLine(0, 3 * D, w, 3 * D, paint);
        canvas.restore();

        // 绘制圆
        canvas.translate(0, D / (count + 1));
        canvas.drawCircle(halfW, R, R, paint);

        // 通过绘制两个圆形成圆环
        // 1. 首先绘制大圆
        canvas.translate(0, D + D / (count + 1));
        canvas.drawCircle(halfW, R, R, paint);
        // 2. 然后绘制小圆，让小圆覆盖大圆，形成圆环效果
        int r = (int) (R * 0.75);
        paint.setColor(0xffffffff); // 将画笔设置为白色，画小圆
        canvas.drawCircle(halfW, R, r, paint);

        // 通过线条绘图模式绘制圆环
        canvas.translate(0, D + D / (count + 1));
        paint.setColor(0xff8bc5ba); // 设置颜色
        paint.setStyle(Paint.Style.STROKE);
        float strokeWidth = (float) (R * 0.25);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawCircle(halfW, R, R, paint);
    }
}
