package com.barry.customview.canvas;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class DrawLoveActivity extends CanvasActivity {
    @Override
    void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        Path animPath = new Path();
        animPath.addArc(200, 200, 400, 400, -225, 255);
        animPath.arcTo(400, 200, 600, 400, -180, 225, false);
        animPath.lineTo(400, 542);

        canvas.drawPath(animPath, paint);
    }
}
