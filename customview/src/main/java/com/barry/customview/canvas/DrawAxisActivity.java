package com.barry.customview.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;

public class DrawAxisActivity extends CanvasActivity {

    public void draw(Canvas canvas) {
        int w = canvas.getWidth();
        int h = canvas.getHeight();

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(6 * mDensity);

        // 用绿色画x轴，用蓝色画y轴

        // 第一次绘制坐标轴
        canvas.translate(6 * mDensity, 6 * mDensity);
        paint.setColor(0xff00ff00); // 绿色
        canvas.drawLine(0, 0, w, 0, paint); // 绘制x轴
        paint.setColor(0xff0000ff); // 蓝色
        canvas.drawLine(0, 0, 0, h, paint); // 绘制y轴

        // 对坐标系平移后，第二次绘制坐标轴
        canvas.translate(w / 4, w / 4);
        paint.setColor(0xff00ff00); // 绿色
        canvas.drawLine(0, 0, w, 0, paint); // 绘制x轴
        paint.setColor(0xff0000ff); // 蓝色
        canvas.drawLine(0, 0, 0, h, paint); // 绘制y轴

        // 再次平移坐标第并在此基础上旋转坐标第，第三次绘制坐标轴
        canvas.translate(w / 4, w / 4);
        canvas.rotate(30); // 基于当前绘图坐标系的原点旋转坐标系
        paint.setColor(0xff00ff00); // 绿色
        canvas.drawLine(0, 0, w, 0, paint); // 绘制x轴
        paint.setColor(0xff0000ff); // 蓝色
        canvas.drawLine(0, 0, 0, h, paint); // 绘制y轴
    }

}