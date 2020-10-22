package com.barry.customview.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;

public class DrawTextActivity extends CanvasActivity {
    @Override
    void draw(Canvas canvas) {
        int w = canvas.getWidth();
        int halfW = w / 2;

        TextPaint paint = new TextPaint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(18 * mDensity);
        paint.setAntiAlias(true);

        Rect rect = new Rect();
        paint.getTextBounds("正常绘制文本", 0, "正常绘制文本".length(), rect);
        float textHeight = rect.height();
        float translateY = textHeight;

        // 绘制正常文本
        canvas.save();
        canvas.translate(0, translateY);
        canvas.drawText("正常绘制文本", 0, 0, paint);
        canvas.restore();
        translateY += textHeight * 2;

        // 绘制绿色文本
        paint.setColor(0xff00ff00); // 设置字体为绿色
        canvas.save();
        canvas.translate(0, translateY);
        canvas.drawText("绘制绿色文本", 0, 0, paint);
        canvas.restore();
        paint.setColor(0xff000000); // 重新设置为黑色
        translateY += textHeight * 2;

        // 设置左对齐
        paint.setTextAlign(Paint.Align.LEFT); // 设置左对齐
        canvas.save();
        canvas.translate(halfW, translateY);
        canvas.drawText("左对齐文本", 0, 0, paint);
        canvas.restore();
        translateY += textHeight * 2;

        // 设置居中对齐
        paint.setTextAlign(Paint.Align.CENTER); // 设置居中对齐
        canvas.save();
        canvas.translate(halfW, translateY);
        canvas.drawText("居中对齐文本", 0, 0, paint);
        canvas.restore();
        translateY += textHeight * 2;

        // 设置右对齐
        paint.setTextAlign(Paint.Align.RIGHT); // 设置右对齐
        canvas.save();
        canvas.translate(halfW, translateY);
        canvas.drawText("右对齐文本", 0, 0, paint);
        canvas.restore();
        paint.setTextAlign(Paint.Align.LEFT); // 重新设置为左对齐
        translateY += textHeight * 2;

        // 设置下划线
        paint.setUnderlineText(true); // 设置具有下划线
        canvas.save();
        canvas.translate(0, translateY);
        canvas.drawText("下划线", 0,0, paint);
        canvas.restore();
        paint.setUnderlineText(false); // 重新设置为没有下划线
        translateY += textHeight * 2;

        // 绘制加粗文字
        paint.setFakeBoldText(true); // 将画笔设置为粗体
        canvas.save();
        canvas.translate(0, translateY);
        canvas.drawText("粗体文字", 0, 0, paint);
        canvas.restore();
        paint.setFakeBoldText(false); // 重新将画笔设置为非粗体状态
        translateY += textHeight * 2;

        // 文本绕绘制起点顺时针旋转
        canvas.save();
        canvas.translate(0, translateY);
        canvas.rotate(20);
        canvas.drawText("文本绕绘制起点旋转20度", 0, 0, paint);
        canvas.restore();
    }
}
