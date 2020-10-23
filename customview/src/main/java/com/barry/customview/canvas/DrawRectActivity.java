package com.barry.customview.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class DrawRectActivity extends CanvasActivity {
    @Override
    void draw(Canvas canvas) {
        int w = canvas.getWidth();
        int h = canvas.getHeight();

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        // 默认画笔是黑色
        Rect rect1 = new Rect(10, 10, w / 3, h / 3);
        canvas.drawRect(rect1, paint);

        // 修改画笔颜色
        paint.setColor(0xff8bc5ba);
        Rect rect2 = new Rect(w / 3 * 2, 10, w - 10, h / 3);
        canvas.drawRect(rect2, paint);
    }
}
