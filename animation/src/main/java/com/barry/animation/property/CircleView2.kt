package com.barry.animation.property

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable

class CircleView2 @JvmOverloads constructor(context: Context?, @Nullable attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
        private var mColor: Int = Color.GREEN
        private var mRadius = 100
        private var mPaint: Paint? = null

//        constructor(context: Context): super(context) {}
//
//        constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet) {}
//
//        constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int): super(context, attributeSet, defStyleAttr){}

        init {
            mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            mPaint?.color = mColor
            mPaint?.strokeWidth = 5f
            mPaint?.style = Paint.Style.STROKE
        }

        fun getColor(): Int {
            return this.mColor
        }

        fun setColor(color: Int) {
            this.mColor = color
            mPaint?.setColor(color)
            invalidate()
        }

        fun getRadius(): Int {
            return this.mRadius
        }

        fun setRadius(radius: Int) {
            this.mRadius = radius
            invalidate()
        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
            canvas?.drawCircle((measuredHeight / 2).toFloat(), (measuredHeight / 2).toFloat(), mRadius.toFloat(), mPaint!!)
        }

        override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            var width = MeasureSpec.getSize(widthMeasureSpec)
            var height = MeasureSpec.getSize(heightMeasureSpec)
            if (width < 200) {
                width = 200
            }
            if (height < 200) {
                height = 200
            }
            if (width < height) {
                setMeasuredDimension(height, height)
            } else {
                setMeasuredDimension(width, width)
            }
        }
        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            super.onSizeChanged(w, h, oldw, oldh)
        }
    }