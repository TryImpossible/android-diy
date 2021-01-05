package com.barry.animation.circular_reveal

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import com.barry.animation.R
import kotlin.math.sqrt

class CircularReveal3Activity : AppCompatActivity() {
    var mIvTargetView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_reveal3)
        mIvTargetView = findViewById(R.id.iv_target_view)
    }

    override fun onResume() {
        super.onResume()
        findViewById<Button>(R.id.btn_start).setOnClickListener {
            val width = mIvTargetView!!.measuredWidth
            val height = mIvTargetView!!.measuredHeight
            val radius = sqrt(width.toDouble() * width.toDouble() + height.toDouble() * height.toDouble()) / 2

            var animator: Animator? = null
            if (mIvTargetView!!.visibility == View.VISIBLE) {
                animator = ViewAnimationUtils.createCircularReveal(mIvTargetView, width / 2, height / 2, radius.toFloat(), 0.0f)
                animator.also {
                    it.duration = 1000
                    it.interpolator = AccelerateDecelerateInterpolator()
                    it.start()
                    it.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            mIvTargetView!!.visibility = View.GONE
                        }
                    })
                }
            } else {
                mIvTargetView!!.visibility = View.VISIBLE
                animator = ViewAnimationUtils.createCircularReveal(mIvTargetView, width / 2, height / 2, 0.0f, radius.toFloat())
                animator.also {
                    it.duration = 1000
                    it.interpolator = AccelerateDecelerateInterpolator()
                    it.start()
                    it.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            mIvTargetView!!.visibility = View.VISIBLE
                        }
                    })
                }
            }
        }

        Handler().postDelayed(Runnable {
            val width = mIvTargetView!!.measuredWidth
            val height = mIvTargetView!!.measuredHeight
            val radius = sqrt(width.toFloat() * width.toFloat() + height.toFloat() * height.toFloat()) / 2

            mIvTargetView!!.visibility = View.VISIBLE

            val animator = ViewAnimationUtils.createCircularReveal(mIvTargetView, width / 2, height /2 ,radius, 20.0f)
            animator.also {
                it.duration = 3000
                it.interpolator = AccelerateDecelerateInterpolator()
                it.start()
                it.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        mIvTargetView!!.visibility = View.GONE
                    }
                })
            }

        }, 1000)
    }
}