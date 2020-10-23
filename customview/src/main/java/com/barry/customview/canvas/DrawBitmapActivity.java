package com.barry.customview.canvas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.barry.customview.R;

public class DrawBitmapActivity extends CanvasActivity {
    @Override
    void draw(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

        Paint paint = new Paint();

        // 直接完全绘制Bitmap
        canvas.drawBitmap(bitmap, 0, 0, paint);

        // 绘制Bitmap的一部分，并对其拉伸
        // srcRect定义了要绘制Bitmap的哪一部分
        Rect srcRect = new Rect();
        srcRect.left = 0;
        srcRect.top = 0;
        srcRect.right = bitmap.getWidth();
        srcRect.bottom = (int) (0.33 * bitmap.getHeight());
        float radio = (float)(srcRect.bottom - srcRect.top) / bitmap.getWidth();
        // dstRecF定义了要将绘制的Bitmap拉伸到哪里
        RectF dstRecF = new RectF();
        dstRecF.left = 0;
        dstRecF.top = bitmap.getHeight();
        dstRecF.right = canvas.getWidth();
        float dstHeight = (dstRecF.right - dstRecF.left) * radio;
        dstRecF.bottom = dstRecF.top + dstHeight;
        canvas.drawBitmap(bitmap, srcRect, dstRecF, paint);
    }
}
