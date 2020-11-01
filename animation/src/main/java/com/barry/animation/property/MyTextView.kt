package com.barry.animation.property

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class MyTextView : AppCompatTextView {
        constructor(context: Context) : super(context) {}

        constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {}

        constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {}

        fun setMuliText(data1: Int, data2: Int) {
            text = (data1 + data2).toString()
        }

        fun getMuliText(): String {
            return getText().toString()
        }
    }