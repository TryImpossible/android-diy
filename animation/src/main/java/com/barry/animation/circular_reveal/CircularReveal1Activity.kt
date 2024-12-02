package com.barry.animation.circular_reveal

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.*
import android.view.View
import android.view.ViewAnimationUtils
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.LinearLayout
import com.barry.animation.R
import kotlin.math.sqrt

class CircularReveal1Activity : AppCompatActivity() {
    var mLLTargetView: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        val fade = Fade()
        window.enterTransition = fade
        window.exitTransition = fade
        window.reenterTransition = fade
        window.returnTransition = fade

        val changeBounds = ChangeBounds()
        changeBounds.duration = 1000
        val changeClipBounds = ChangeClipBounds()
        changeClipBounds.duration = 1000
        val changeImageTransform = ChangeImageTransform()
        changeImageTransform.duration = 1000
        val changeTransform = ChangeTransform()
        changeTransform.duration = 1000
        window.sharedElementEnterTransition = changeBounds
        window.sharedElementExitTransition = changeClipBounds
        window.sharedElementReenterTransition = changeImageTransform
        window.sharedElementReturnTransition = changeTransform

        window.sharedElementsUseOverlay = true

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_reveal1)

        mLLTargetView = findViewById<LinearLayout>(R.id.ll_target_view)
    }

    override fun onResume() {
        super.onResume()
        findViewById<Button>(R.id.btn_start).setOnClickListener {
            val width = mLLTargetView!!.measuredWidth
            val height = mLLTargetView!!.measuredHeight
            val radius = sqrt(width.toDouble() * width.toDouble() + height.toDouble() * height.toDouble()) / 2

            var animator: Animator? = null
            if (mLLTargetView!!.visibility == View.VISIBLE) {
                animator = ViewAnimationUtils.createCircularReveal(mLLTargetView, width / 2, height / 2, radius.toFloat(), 0.0f)
                animator.also {
                    it.duration = 1000
                    it.interpolator = AccelerateDecelerateInterpolator()
                    it.start()
                    it.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            mLLTargetView!!.visibility = View.GONE
                        }
                    })
                }
            } else {
                mLLTargetView!!.visibility = View.VISIBLE
                animator = ViewAnimationUtils.createCircularReveal(mLLTargetView, width/ 2, height / 2, 0.0f, radius.toFloat())
                animator.also {
                    it.duration = 1000
                    it.interpolator = AccelerateDecelerateInterpolator()
                    it.start()
                    it.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            mLLTargetView!!.visibility = View.VISIBLE
                        }
                    })
                }
            }
        }
    }
}