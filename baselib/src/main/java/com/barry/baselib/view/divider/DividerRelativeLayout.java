/*
 *
 * *******************************************************************
 *   @项目名称: BHex Android
 *   @文件名称: DividerRelativeLayout.java
 *   @Date: 11/29/18 3:21 PM
 *   @Author: chenjun
 *   @Copyright（C）: 2018 BlueHelix Inc.   All rights reserved.
 *   注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的.
 *  *******************************************************************
 *
 */

package com.barry.baselib.view.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;


public class DividerRelativeLayout extends RelativeLayout {

    private DividerLayoutHelper mHelper;

    public DividerRelativeLayout(Context context) {
        super(context);
        init(context, null);
    }

    public DividerRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DividerRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        mHelper = new DividerLayoutHelper(this, attrs);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);

        if (mHelper != null)
            mHelper.updatePaddingInfo();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mHelper.onDraw(canvas);
    }

    public DividerLayoutHelper getDividerHelper() {
        return mHelper;
    }
}
