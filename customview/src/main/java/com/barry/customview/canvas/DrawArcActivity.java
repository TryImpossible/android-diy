package com.barry.customview.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class DrawArcActivity extends CanvasActivity {
    @Override
    void draw(Canvas canvas) {
        Paint paint = new Paint();

        int w = canvas.getWidth();
        int h = canvas.getHeight();
        int count = 5;
        float ovalH = h / (count + 1);
        float left = 10 * mDensity;
        float top = 0;
        float right = w - left;
        float bottom = ovalH;
        RectF rectF = new RectF(left, top, right, bottom);

        paint.setStrokeWidth(2 * mDensity); // 设置线宽
        paint.setColor(0xff8bc5ba); // 设置颜色
        paint.setStyle(Paint.Style.FILL); // 设置画笔为填充模式

        // 绘制用drawArc绘制完整的圆
        canvas.translate(0, ovalH / count);
        canvas.drawArc(rectF, 0, 360, true, paint);

        // 绘制椭圆的四分之一，起点是钟表3点的位置，从3点绘制到6点的位置
        canvas.translate(0, (ovalH + ovalH / count));
        canvas.drawArc(rectF, 0, 90, true, paint);

        // 绘制椭圆的四分之一，将useCenter设置为false
        canvas.translate(0, (ovalH + ovalH / count));
        canvas.drawArc(rectF, 0, 90, false, paint);

        // 绘制椭圆的四分之一，只绘制轮廓线
        paint.setStyle(Paint.Style.STROKE); // 设置画笔为线条模式
        canvas.translate(0, (ovalH + ovalH / count));
        canvas.drawArc(rectF, 0, 90, true, paint);

        // 绘制带有轮廓线的椭圆的四分之一
        // 1. 先绘制椭圆的填充部分
        paint.setStyle(Paint.Style.FILL); // 设置画笔为填充模式
        canvas.translate(0, (ovalH + ovalH / count));
        canvas.drawArc(rectF, 0, 90, true, paint);
        // 2. 再绘制椭圆的轮廓部分
        paint.setStyle(Paint.Style.STROKE); // 设置画笔为线条模式
        paint.setColor(0xff0000ff); // 设置轮廓线条为蓝色
        canvas.drawArc(rectF, 0, 90, true, paint);
    }
}
