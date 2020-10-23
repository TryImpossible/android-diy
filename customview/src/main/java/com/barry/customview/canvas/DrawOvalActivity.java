package com.barry.customview.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class DrawOvalActivity extends CanvasActivity {
    @Override
    void draw(Canvas canvas) {
        Paint paint = new Paint();

        int w = canvas.getWidth();
        int h = canvas.getHeight();
        float quarter = h / 4;
        float left = 10 * mDensity;
        float top = 0;
        float right = w - left;
        float bottom = quarter;
        RectF rectF = new RectF(left, top, right, bottom);

        // 绘制椭圆形轮廓线
        paint.setStyle(Paint.Style.STROKE); // 设置画笔为画线条模式
        paint.setStrokeWidth(2 * mDensity); // 设置线宽
        paint.setColor(0xff8bc5ba); // 设置线条颜色
        canvas.translate(0, quarter / 4);
        canvas.drawOval(rectF, paint);

        // 绘制椭圆形填充面
        paint.setStyle(Paint.Style.FILL); // 设置画笔为填充模式
        canvas.translate(0, (quarter + quarter / 4));
        canvas.drawOval(rectF, paint);

        // 画两个椭圆，形成轮廓线和填充色不同的效果
        canvas.translate(0, (quarter + quarter / 4));
        // 1. 首先绘制填充色
        paint.setStyle(Paint.Style.FILL); // 设置画笔为填充模式
        canvas.drawOval(rectF, paint); //绘制椭圆形的填充效果
        // 2. 将线条颜色设置为蓝色，绘制轮廓线
        paint.setStyle(Paint.Style.STROKE);//设置画笔为线条模式
        paint.setColor(0xff0000ff);//设置填充色为蓝色
        canvas.drawOval(rectF, paint);//设置椭圆的轮廓线

    }
}
