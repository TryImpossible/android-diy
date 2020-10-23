package com.barry.customview.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;

public class DrawRulerActivity extends CanvasActivity {
    // 刻度尺高度
    private static final int DIVIDING_RULE_HEIGHT = 70;
    // 左右距离
    private static final int DIVIDING_RULE_MARGIN_LEFT_RIGHT = 10;

    // 第一条线距离边框距离
    private static final int FIRST_LINE_MARGIN = 5;
    // 打算绘制的厘米数
    private static final int DEFAULT_COUNT = 9;

    private int mTotalWidth;
    private int mDividRuleHeight;
    private int mDividRuleLeftMargin;
    private int mFirstLineMargin;
    private Rect mOutRect;
    private int mLineInterval;

    private Paint mOuterPaint;
    private Paint mLinePaint;

    private int mMaxLineTop;
    private int mMiddleLineTop;
    private int mMinLineTop;

    @Override
    void draw(Canvas canvas) {

        initData(canvas);
        // 绘制外框
        drawOuter(canvas);
        // 绘制刻度线
        drawLines(canvas);
        // 绘制数字
        drawNumbers(canvas);
    }

    private void initData(Canvas canvas) {
        mTotalWidth = canvas.getWidth();

        mDividRuleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DIVIDING_RULE_HEIGHT, getResources().getDisplayMetrics());

        mDividRuleLeftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DIVIDING_RULE_MARGIN_LEFT_RIGHT, getResources().getDisplayMetrics());
        mFirstLineMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, FIRST_LINE_MARGIN, getResources().getDisplayMetrics());

        mMaxLineTop = mDividRuleHeight / 2;
        mMiddleLineTop = mDividRuleHeight / 2 / 4 * 3;
        mMinLineTop = mDividRuleHeight / 2 / 2;


        mOutRect = new Rect(mDividRuleLeftMargin, 30, mTotalWidth - mDividRuleLeftMargin, mDividRuleHeight);

        mLineInterval = (mTotalWidth - 2 * mDividRuleLeftMargin - 2 * mFirstLineMargin) / (DEFAULT_COUNT * 10 - 1);

        mOuterPaint = new Paint();
        mLinePaint = new Paint();
    }

    private void drawOuter(Canvas canvas) {
        mOuterPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(mOutRect, mOuterPaint);
    }

    private void drawLines(Canvas canvas) {
        canvas.save();
        canvas.translate(mDividRuleLeftMargin + mFirstLineMargin, mDividRuleHeight);
        int top = mMaxLineTop;
        for (int i = 0; i <= DEFAULT_COUNT * 10; i++) {
            if (i % 10 == 0) {
                top = mMaxLineTop;
            } else if (i % 5 == 0) {
                top = mMiddleLineTop;
            } else {
                top = mMinLineTop;
            }
            canvas.drawLine(0, 0, 0, -top, mLinePaint);
            canvas.translate(mLineInterval, 0);
        }
        canvas.restore();

    }

    private void drawNumbers(Canvas canvas) {

    }
}
