package com.barry.customview.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FourCornerView extends ViewGroup {
    public FourCornerView(Context context) {
        this(context, null);
    }

    public FourCornerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FourCornerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FourCornerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 测量出所有childview的宽高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int count = getChildCount();
        int width = 0, height = 0;

        int topWidth = 0, bottomWidth = 0;
        int leftHeight = 0, rightHeight = 0;

        View childView;
        int childWidth, childHeight;
        MarginLayoutParams childParams;
        for (int i = 0; i < count; i++) {
            childView = getChildAt(i);
            childWidth = childView.getMeasuredWidth();
            childHeight = childView.getMeasuredHeight();
            childParams = (MarginLayoutParams) childView.getLayoutParams();
            // 左侧的高度
            if (i == 0 || i == 2) {
                leftHeight += childParams.topMargin + childHeight + childParams.bottomMargin;
            }
            // 上侧的宽度
            if (i == 0 || i == 1) {
                topWidth += childParams.leftMargin + childWidth + childParams.rightMargin;
            }
            // 右侧的高度
            if (i == 0 || i == 2) {
                rightHeight += childParams.topMargin + childHeight + childParams.bottomMargin;
            }
            // 下侧的宽度
            if (i == 0 || i == 1) {
                bottomWidth += childParams.leftMargin + childWidth + childParams.rightMargin;
            }
        }
        // 取最大值
        width = Math.max(topWidth, bottomWidth);
        // 取最大值
        height = Math.max(leftHeight, rightHeight);
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width, heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            switch (i) {
                case 0:
                    childView.layout(0, 0, childWidth, childHeight);
                    break;
                case 1:
                    childView.layout(getMeasuredWidth() - childWidth, 0, getMeasuredWidth(), childHeight);
                    break;
                case 2:
                    childView.layout(0, getMeasuredHeight() - childHeight, childWidth, getMeasuredHeight());
                    break;
                case 3:
                    childView.layout(getMeasuredWidth() - childWidth, getMeasuredHeight() - childHeight, getMeasuredWidth(), getMeasuredHeight());
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
