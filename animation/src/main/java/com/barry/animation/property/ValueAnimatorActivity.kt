package com.barry.animation.property

import android.animation.*
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.barry.animation.R

class ValueAnimatorActivity : AppCompatActivity(), View.OnClickListener {
    private var mImageView: ImageView? = null
    private var mTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_animator)

        mImageView = findViewById<ImageView>(R.id.iv_image)
        mTextView = findViewById(R.id.tv_text)

        findViewById<Button>(R.id.btn_ofInt).setOnClickListener(this)
        findViewById<Button>(R.id.btn_ofFloat).setOnClickListener(this)
        findViewById<Button>(R.id.btn_ofArgb).setOnClickListener(this)
        findViewById<Button>(R.id.btn_ofObject).setOnClickListener(this)
        findViewById<Button>(R.id.btn_ofProperty).setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_ofInt -> {
                val animator = ValueAnimator.ofInt(1, 10)
                animator.duration = 1000;
                animator.addUpdateListener {
                    val data = it.animatedValue.toString().toFloat()
                    System.out.println("========getAnimatedValue=========$data")
                }
                animator.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationCancel(animation: Animator?) {
                        super.onAnimationCancel(animation)
                    }
                })
                if (animator.isRunning) {
                    animator.cancel()
                }
                animator.start()
            }
            R.id.btn_ofFloat -> {
                var animator = AnimatorInflater.loadAnimator(this, R.animator.of_float) as ValueAnimator
                animator.addUpdateListener {
                    val data = it.animatedValue.toString().toFloat();
                    System.out.println("========getAnimatedValue=========$data")
                    val matrix = Matrix()
                    matrix.setScale(data, data)
                    //ImageView要支持matrix,需要设置ImageView的ScaleType为matrix
                    mImageView?.imageMatrix = matrix
                }
                animator.setTarget(mImageView)
                if (animator.isRunning) {
                    animator.cancel()
                }
                animator.start()
            }
            R.id.btn_ofArgb -> {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    var animator = ValueAnimator.ofArgb(Color.RED, Color.GREEN)
                    animator.duration = 3000
                    animator.addUpdateListener {
                        val data = it.animatedValue.toString().toInt()
                        mTextView?.setBackgroundColor(data);
                    }
                    if (animator.isRunning) {
                        animator.cancel()
                    }
                    animator.start()
                }
            }
            R.id.btn_ofObject -> {
                var heightAndColor1 = HeightAndColor()
                heightAndColor1.height = 200
                heightAndColor1.color = Color.RED
                var heightAndColor2 = HeightAndColor()
                heightAndColor1.height = 400
                heightAndColor1.color = Color.GREEN

                var animator = ValueAnimator.ofObject(HeightAndColorEvaluator(), heightAndColor1, heightAndColor2)
                animator.duration = 3000
                animator.addUpdateListener(ValueAnimator.AnimatorUpdateListener {
                    val data = it.animatedValue as HeightAndColor

                    mTextView?.setBackgroundColor(data.color)
                    val lp = mTextView?.layoutParams
                    lp?.height = data.height
                    mTextView?.layoutParams = lp
                })
                animator.start()
            }
            R.id.btn_ofProperty -> {
                val propertyValuesHolder1 = PropertyValuesHolder.ofInt("color", Color.RED, Color.GREEN)
                val propertyValuesHolder2 = PropertyValuesHolder.ofInt("height", 200, 400)
                val animator = ValueAnimator.ofPropertyValuesHolder(propertyValuesHolder1, propertyValuesHolder2)
                animator.duration = 1000
                animator.interpolator = LinearInterpolator()
                animator.addUpdateListener {
                    val color = it.getAnimatedValue("color") as Int
                    val height = it.getAnimatedValue("height") as Int

                    mTextView?.setBackgroundColor(color)
                    val lp = mTextView?.layoutParams
                    lp?.height = height
                    mTextView?.layoutParams = lp
                }
                animator.start()
            }
        }
    }

    inner class HeightAndColor {
        var color = 0
        var height = 0
    }

    inner class HeightAndColorEvaluator : TypeEvaluator<HeightAndColor> {
        override fun evaluate(fraction: Float, startValue: HeightAndColor, endValue: HeightAndColor): HeightAndColor {
            val startHeight = startValue.height
            var currentHeight = (startHeight + fraction * (endValue.height - startHeight)).toInt()
            var currentColor = getCurrentRGBA(fraction, startValue.color, endValue.color)
            var heightAndColor = HeightAndColor()
            heightAndColor.color = currentColor
            heightAndColor.height = currentHeight
            return heightAndColor
        }

        fun getCurrentRGBA(fraction: Float, startValue: Int, endValue: Int): Int {
            val startA = (startValue shr 24 and 0xff) / 255.0f
            var startR = (startValue shr 16 and 0xff) / 255.0f
            var startG = (startValue shr 8 and 0xff) / 255.0f
            var startB = (startValue and 0xff) / 255.0f
            val endA = (endValue shr 24 and 0xff) / 255.0f
            var endR = (endValue shr 16 and 0xff) / 255.0f
            var endG = (endValue shr 8 and 0xff) / 255.0f
            var endB = (endValue and 0xff) / 255.0f

            // convert from sRGB to linear
            startR = Math.pow(startR.toDouble(), 2.2).toFloat()
            startG = Math.pow(startG.toDouble(), 2.2).toFloat()
            startB = Math.pow(startB.toDouble(), 2.2).toFloat()
            endR = Math.pow(endR.toDouble(), 2.2).toFloat()
            endG = Math.pow(endG.toDouble(), 2.2).toFloat()
            endB = Math.pow(endB.toDouble(), 2.2).toFloat()

            // compute the interpolated color in linear space
            var a = startA + fraction * (endA - startA)
            var r = startR + fraction * (endR - startR)
            var g = startG + fraction * (endG - startG)
            var b = startB + fraction * (endB - startB)

            // convert back to sRGB in the [0..255] range
            a *= 255.0f
            r = Math.pow(r.toDouble(), 1.0 / 2.2).toFloat() * 255.0f
            g = Math.pow(g.toDouble(), 1.0 / 2.2).toFloat() * 255.0f
            b = Math.pow(b.toDouble(), 1.0 / 2.2).toFloat() * 255.0f
            return Math.round(a) shl 24 or (Math.round(r) shl 16) or (Math.round(g) shl 8) or Math.round(b)
        }
    }
}