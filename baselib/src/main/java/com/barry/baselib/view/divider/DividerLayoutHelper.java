/*
 *
 * *******************************************************************
 *   @项目名称: BHex Android
 *   @文件名称: DividerLayoutHelper.java
 *   @Date: 11/29/18 3:21 PM
 *   @Author: chenjun
 *   @Copyright（C）: 2018 BlueHelix Inc.   All rights reserved.
 *   注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的.
 *  *******************************************************************
 *
 */

package com.barry.baselib.view.divider;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;

import com.barry.baselib.R;
import com.barry.baselib.utils.PixelUtils;


public class DividerLayoutHelper {
    private ViewGroup viewGroup;

    private static final int NO_DIVIDER = 0x0000;


    private static final int LEFT_DIVIDER = 0x0001;
    private static final int RIGHT_DIVIDER = 0x0002;
    private static final int TOP_DIVIDER = 0x0004;
    private static final int BOTTOM_DIVIDER = 0x0008;

    private static final int MASK = 0x000F;

    private int dividerColor;

    private int divider;

    private Paint mPaint;

    private int dividerWidth;

    private int[] dividerPaddings;

    boolean canUpdate;

    public DividerLayoutHelper(ViewGroup viewGroup, AttributeSet attrs) {
        this.viewGroup = viewGroup;

        viewGroup.setWillNotDraw(false);

        dividerPaddings = new int[4];

        if (viewGroup.isInEditMode()) {
            dividerWidth = 2;
        }
        if (attrs != null && !viewGroup.isInEditMode()) {
            TypedArray ta = viewGroup.getContext().obtainStyledAttributes(attrs, R.styleable.DividerLayout);
            divider = ta.getInteger(R.styleable.DividerLayout_divider_direction, NO_DIVIDER);

            dividerColor = ta.getColor(R.styleable.DividerLayout_divider_color, Color.TRANSPARENT);

            dividerWidth = ta.getDimensionPixelSize(R.styleable.DividerLayout_divider_width, PixelUtils.dp2px(0.5f));

            dividerPaddings[0] = ta.getDimensionPixelSize(R.styleable.DividerLayout_divider_paddingLeft, 0);
            dividerPaddings[1] = ta.getDimensionPixelSize(R.styleable.DividerLayout_divider_paddingTop, 0);
            dividerPaddings[2] = ta.getDimensionPixelSize(R.styleable.DividerLayout_divider_paddingRight, 0);
            dividerPaddings[3] = ta.getDimensionPixelSize(R.styleable.DividerLayout_divider_paddingBottom, 0);
        }

        updatePaddingInfo();

        mPaint = new Paint();
        mPaint.setColor(dividerColor);
    }

    void updatePaddingInfo() {

        if (!canUpdate)
            return;

        canUpdate = false;
        try {
            int paddingLeft = viewGroup.getPaddingLeft();
            int paddingRight = viewGroup.getPaddingRight();
            int paddingTop = viewGroup.getPaddingTop();
            int paddingBottom = viewGroup.getPaddingBottom();

            switch (divider & MASK) {
                case LEFT_DIVIDER:
                    paddingLeft += (dividerWidth + dividerPaddings[0]);
                    break;
                case RIGHT_DIVIDER:
                    paddingRight += (dividerWidth + dividerPaddings[2]);
                    break;
                case TOP_DIVIDER:
                    paddingTop += (dividerWidth + dividerPaddings[1]);
                    break;
                case BOTTOM_DIVIDER:
                    paddingBottom += (dividerWidth + dividerPaddings[3]);
                    break;
            }

            viewGroup.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        } finally {
            canUpdate = true;
        }
    }

    void onDraw(Canvas canvas) {

        int width = viewGroup.getMeasuredWidth();
        int height = viewGroup.getMeasuredHeight();

        if ((divider & LEFT_DIVIDER) > 0)
            canvas.drawRect(dividerPaddings[0], dividerPaddings[1], dividerWidth + dividerPaddings[0], height - dividerPaddings[3], mPaint);

        if ((divider & RIGHT_DIVIDER) > 0)
            canvas.drawRect(width - dividerWidth - dividerPaddings[2], dividerPaddings[1], width - dividerPaddings[2], height - dividerPaddings[3], mPaint);

        if ((divider & TOP_DIVIDER) > 0)
            canvas.drawRect(dividerPaddings[0], dividerPaddings[1], width - dividerPaddings[2], dividerWidth + dividerPaddings[1], mPaint);

        if ((divider & BOTTOM_DIVIDER) > 0)
            canvas.drawRect(dividerPaddings[0], height - dividerWidth - dividerPaddings[3],
                    width - dividerPaddings[2], height - dividerPaddings[3], mPaint);
    }

    public void setDividerColor(@ColorRes int color) {
        this.dividerColor = viewGroup.getContext().getResources().getColor(color);
        mPaint.setColor(dividerColor);
    }
}
