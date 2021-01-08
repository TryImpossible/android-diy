package com.barry.customview.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.barry.customview.R;

import java.util.Random;

class RandomNumView extends View {

    private Context mContext;
    private String mText;
    private int mTextColor;
    private int mTextSize;

    private Rect mRect;
    private Paint mPaint;
    private Canvas mCanvas;

    public RandomNumView(Context context) {
        this(context, null);
    }

    public RandomNumView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RandomNumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RandomNumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        mTextColor = Color.BLACK;
        mTextSize = sp2px((14));

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RandomNumView, defStyleAttr, 0);
        if (typedArray != null) {
            mText = typedArray.getString(R.styleable.RandomNumView_text);
            mTextColor = typedArray.getColor(R.styleable.RandomNumView_textColor, mTextColor);
            mTextSize = typedArray.getDimensionPixelSize(R.styleable.RandomNumView_textSize, mTextSize);
        }
        typedArray.recycle();

        mText = mText == null ? "" : mText;

        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mRect = new Rect();
        mCanvas = new Canvas();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = randomText();
                postInvalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.getTextBounds(mText, 0, mText.length(), mRect);

        mPaint.setColor(mTextColor);
        canvas.drawText(mText, getWidth() / 2 - mRect.width() / 2, getHeight() / 2 + mRect.height() / 2, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0, height = 0;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mRect);
            float textWidth = mRect.width();
            width = (int) (getPaddingLeft() + textWidth + getPaddingRight());
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mRect);
            float texHeight = mRect.height();
            height = (int) (getPaddingTop() + texHeight + getPaddingBottom());
        }

        setMeasuredDimension(width, height);
    }

    private int sp2px(int sp) {
        float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
        return (int) (scaledDensity * sp + 0.5);
    }

    private String randomText() {
        return String.format("%04d", new Random().nextInt(9999));
    }


}