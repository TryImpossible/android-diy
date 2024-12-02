package com.barry.animation.circular_reveal

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationSet
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityOptionsCompat
import com.barry.animation.R

class CircularReveal2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_reveal2)

        val mClContainer = findViewById<ConstraintLayout>(R.id.cl_container)
        val mIvFloatActionBtn = findViewById<ImageView>(R.id.iv_float_action_btn)
        val mFrameLayout = findViewById<FrameLayout>(R.id.frame_layout)
        mFrameLayout.visibility = View.GONE
        mIvFloatActionBtn.setOnClickListener {
            val path = Path()
            path.moveTo((mClContainer.measuredWidth - mIvFloatActionBtn.width).toFloat(), (mClContainer.measuredHeight - mIvFloatActionBtn.height).toFloat())
            path.quadTo((mClContainer.measuredWidth - 300).toFloat(), (mClContainer.measuredHeight - 200).toFloat(), (mClContainer.measuredWidth / 2 - mIvFloatActionBtn.width).toFloat(), (mClContainer.measuredHeight / 2 - mIvFloatActionBtn.height).toFloat())
            val objectAnimator = ObjectAnimator.ofFloat(mIvFloatActionBtn, "X", "Y", path)
            objectAnimator.duration = 3000
            objectAnimator.interpolator = AccelerateDecelerateInterpolator()

            val path2 = Path()
            path2.moveTo(1.0f, 1.0f)
            path2.lineTo(2.0f, 2.0f)
            path2.lineTo(1.0f, 1.0f)
            path2.lineTo(2.0f, 2.0f)
            path2.lineTo(1.0f, 1.0f)
            val objectAnimator2 = ObjectAnimator.ofFloat(mIvFloatActionBtn, View.SCALE_X, View.SCALE_Y, path2)
            objectAnimator2.duration = 4000
            objectAnimator2.interpolator = AccelerateDecelerateInterpolator()
            objectAnimator2.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    val intent = Intent(this@CircularReveal2Activity, CircularReveal3Activity().javaClass)
                    val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@CircularReveal2Activity, mIvFloatActionBtn, "shareElement")
                    startActivity(intent, activityOptionsCompat.toBundle())
                }

                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                }
            });

            val animationSet = AnimatorSet()
            animationSet.playSequentially(objectAnimator, objectAnimator2)
            animationSet.start()

        }
    }
}